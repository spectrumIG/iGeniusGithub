package it.playground.igeniusgithub.domain.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toFlow
import it.playground.igeniusgithub.ListAllUserRepoQuery
import it.playground.igeniusgithub.SearchForRepoQuery
import it.playground.igeniusgithub.SearchForUserQuery
import it.playground.igeniusgithub.di.DatastoringModule.TokenLocalDataSource
import it.playground.igeniusgithub.di.DatastoringModule.TokenRemoteDataSource
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    @TokenRemoteDataSource private val remoteDataSource: DataSource,
    @TokenLocalDataSource private val localDataSource: DataSource,
    private val apolloClient: ApolloClient,
) : Repository {

    override suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String> {
        var askedToken = localDataSource.askForToken(clientId, clientSecret, code)

        if(askedToken is Result.Error) {
            askedToken = remoteDataSource.askForToken(clientId, clientSecret, code)

            if(askedToken.succeded) {
                localDataSource.saveTokenLocally((askedToken as Result.Success).data)
            }
        }
        return askedToken
    }

    override suspend fun retrieveLoggedUserRepositories(): Flow<Response<ListAllUserRepoQuery.Data>> {
        return apolloClient.query(ListAllUserRepoQuery()).toFlow()
    }

    @ExperimentalCoroutinesApi
    override suspend fun searchForUserByFilter(filter: String): Flow<Response<SearchForUserQuery.Data>> {
        return apolloClient.query(SearchForUserQuery(filter)).toFlow()

    }

    @ExperimentalCoroutinesApi
    override suspend fun searchForRepoByFilter(filter: String): Flow<Response<SearchForRepoQuery.Data>> {
        return apolloClient.query(SearchForRepoQuery(filter)).toFlow()
    }
}


package it.playground.igeniusgithub.domain.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toFlow
import it.playground.igeniusgithub.ListAllUserRepoQuery
import it.playground.igeniusgithub.di.DatastoringModule.TokenLocalDataSource
import it.playground.igeniusgithub.di.DatastoringModule.TokenRemoteDataSource
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.flow.Flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Named

class DefaultRepository @Inject constructor(
    @TokenRemoteDataSource private val remoteDataSource: DataSource,
    @TokenLocalDataSource private val localDataSource: DataSource,
    private val apolloClientBuilder: ApolloClient.Builder,
    @Named("GraphQLInstance") private val okHttpClient: OkHttpClient.Builder
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
    override suspend fun retrieveUserRepositories(): Flow<Response<ListAllUserRepoQuery.Data>> {
        val token = (localDataSource.askForToken(null, null, null) as  Result.Success<String>).data
        val apolloClient = apolloClientBuilder.okHttpClient(okHttpClient.addInterceptor(OkHttpGraphInterceptor(token)).build()).build()
        return apolloClient.query(ListAllUserRepoQuery()).toFlow()
    }
}

private class OkHttpGraphInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request: Request = chain.request()
        request = request.newBuilder().addHeader("Authorization", "bearer $token").build()
        return chain.proceed(request)
    }
}
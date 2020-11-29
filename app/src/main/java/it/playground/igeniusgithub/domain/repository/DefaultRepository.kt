package it.playground.igeniusgithub.domain.repository

import it.playground.igeniusgithub.di.DatastoringModule
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.local.LocalDataSource
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    @DatastoringModule.TokenRemoteDataSource private val remoteDataSource: DataSource,
    @DatastoringModule.TokenLocalDataSource private val localDataSource: DataSource
) : Repository {

    override suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String> {
        var askedToken = localDataSource.askForToken(clientId, clientSecret, code)

        if(askedToken is Result.Error) {
            askedToken = remoteDataSource.askForToken(clientId, clientSecret, code)

            if(askedToken.succeded){
                (localDataSource as LocalDataSource).saveTokenLocally((askedToken as Result.Success).data)
            }
        }
        return askedToken
    }

}
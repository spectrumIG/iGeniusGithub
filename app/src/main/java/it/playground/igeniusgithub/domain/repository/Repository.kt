package it.playground.igeniusgithub.domain.repository

import it.playground.igeniusgithub.di.CoreModule
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.local.LocalDataSource
import timber.log.Timber
import javax.inject.Inject

interface Repository {
    suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String>
}

class DefaultRepository @Inject constructor(
    @CoreModule.TokenRemoteDataSource private val remoteDataSource: DataSource,
    @CoreModule.TokenLocalDataSource private val localDataSource: DataSource
) : Repository{

    override suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String> {

        var askedToken = localDataSource.askForToken(clientId, clientSecret, code)

        if(askedToken is Result.Error) {

            askedToken = remoteDataSource.askForToken(clientId, clientSecret, code)

            if(askedToken.succeded){
                (localDataSource as LocalDataSource).saveTokenLocally((askedToken as Result.Success).data)
            }
            Timber.i("CODE: FOR GRAPHQL $askedToken")
        }
        return askedToken
    }

}
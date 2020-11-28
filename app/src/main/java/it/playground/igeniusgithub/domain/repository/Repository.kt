package it.playground.igeniusgithub.domain.repository

import it.playground.igeniusgithub.di.CoreModule
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.local.LocalDataSource
import javax.inject.Inject

interface Repository {
    suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String>
}

class DefaultRepository @Inject constructor(
    @CoreModule.TokenRemoteDataSource private val remoteDataSource: DataSource,
    @CoreModule.TokenLocalDataSource private val localDataSource: DataSource
) : Repository{

    override suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String> {
        val askForToken = localDataSource.askForToken(clientId, clientSecret, code)
        return if(askForToken is Result.Error) {

            val remoteAsk = remoteDataSource.askForToken(clientId, clientSecret, code)

            if(remoteAsk.succeded){
                (localDataSource as LocalDataSource).saveTokenLocally((remoteAsk as Result.Success).data)
            }
            remoteAsk
        }else{
            askForToken
        }
    }

}
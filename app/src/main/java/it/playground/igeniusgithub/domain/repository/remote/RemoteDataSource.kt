package it.playground.igeniusgithub.domain.repository.remote

import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.network.OAuthApi
import it.playground.igeniusgithub.domain.repository.DataSource

class RemoteDataSource constructor(private val restApi: OAuthApi) : DataSource {

    override suspend fun askForToken(clientId: String?, clientSecret: String?, code: String?): Result<String> {
        val login = restApi.login(clientId!!, clientSecret!!, code!!)
        return if(login.isSuccessful) {
            Result.Success(login.body()?.accessToken!!)
        } else {
            Result.Error(Exception(login.errorBody().toString()))
        }
    }
}
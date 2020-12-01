package it.playground.igeniusgithub.login

import it.playground.igeniusgithub.di.CoreModule.ClientId
import it.playground.igeniusgithub.di.CoreModule.ClientSecret
import it.playground.igeniusgithub.di.CoreModule.LoginUrlTemplate
import it.playground.igeniusgithub.domain.model.usecase.UseCase
import it.playground.igeniusgithub.domain.repository.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository,
    @ClientId private val clientId: String,
    @ClientSecret private val clientSecret: String,
    @LoginUrlTemplate private val loginTemplateUrl: String
) : UseCase {


    fun constructCorrectUrl(): String {
        return String.format(loginTemplateUrl, clientId)
    }

    override suspend fun invoke() {

    }

    suspend fun retrieveAndSaveToken(code: String): Boolean {
        val retrieveTokenAndStoreIt =
            repository.retrieveTokenAndStoreIt(clientId,clientSecret, code)
        return retrieveTokenAndStoreIt.succeded


    }
}
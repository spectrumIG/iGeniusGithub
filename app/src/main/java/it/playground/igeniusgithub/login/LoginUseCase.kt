package it.playground.igeniusgithub.login

import android.content.Context
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.domain.model.usecase.UseCase
import it.playground.igeniusgithub.domain.repository.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) : UseCase {

    @Inject
    lateinit var context: Context //<- ugly but necessary
    lateinit var scopes: List<String>


    fun constructCorrectUrl(): String {
        val clientId = context.getString(R.string.client_id)
        return context.getString(R.string.login_url, clientId)
    }

    override suspend fun invoke() {

    }

    suspend fun retrieveAndSaveToken(code: String): Boolean {
        val retrieveTokenAndStoreIt =
            repository.retrieveTokenAndStoreIt(context.getString(R.string.client_id), context.getString(R.string.client_secret), code)
        return retrieveTokenAndStoreIt.succeded


    }
}
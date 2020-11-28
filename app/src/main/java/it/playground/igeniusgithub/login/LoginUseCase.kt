package it.playground.igeniusgithub.login

import android.content.Context
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.domain.model.usecase.UseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(): UseCase {

    @Inject
    lateinit var context: Context //<- ugly but necessary
    lateinit var scopes: List<String>


    suspend fun constructCorrectUrl(): String {
        val clientId = context.getString(R.string.client_id)
        return context.getString(R.string.login_url,clientId)
    }

    override suspend fun invoke() {}

//    override suspend fun invoke(option: List<String>?) {
//        scopes = option!!
//    }


}
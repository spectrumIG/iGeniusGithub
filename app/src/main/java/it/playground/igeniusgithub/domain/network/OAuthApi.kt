package it.playground.igeniusgithub.domain.network

import it.playground.igeniusgithub.domain.model.network.AuthorizeResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface OAuthApi {
    @Headers("Accept: application/json")
    @POST("access_token")
    suspend fun login(@Query("client_id") clientId: String,
                      @Query("client_secret") clientSecret: String,
                      @Query("code") authCode: String): Response<AuthorizeResponse>

}
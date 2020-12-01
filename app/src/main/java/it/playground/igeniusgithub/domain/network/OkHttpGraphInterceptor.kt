package it.playground.igeniusgithub.domain.network

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import javax.inject.Inject

class OkHttpGraphInterceptor @Inject constructor(private var token: String, private val preferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        if(token.isNullOrEmpty() ){
            token = preferences.getString("auth_token","")!!
        }
        var request: Request = chain.request()
        request = request.newBuilder().addHeader("Authorization", "bearer $token").build()
        return chain.proceed(request)
    }
}
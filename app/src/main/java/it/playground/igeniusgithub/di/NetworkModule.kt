package it.playground.igeniusgithub.it.playground.igeniusgithub.di

import dagger.Module
import dagger.Provides
import it.playground.igeniusgithub.BuildConfig
import it.playground.igeniusgithub.it.playground.igeniusgithub.domain.network.OAuthApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object NetworkModule {

    object BaseUrl {
        val BASE_GRAPHQL_URL = "https://api.github.com/graphql"
        val BASE_LOGIN_AUTHORIZE = "https://github.com/login/oauth/authorize"
        val BASE_LOGIN_POST_ACCESS_TOKEN = "https://github.com/login/oauth/access_token"

    }

//    @Singleton
//    @Provides
//    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
//        val contentType = "application/json"
//        return Retrofit.Builder()
//            .addConverterFactory(json.asConverterFactory(MediaType.get(contentType)))
//            .baseUrl(BaseUrl.getUrl())
//            .client(okHttpClient)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideJsonConfiguration(): Json {
//        return  Json(
//            JsonConfiguration(
//                encodeDefaults = false,
//                ignoreUnknownKeys = true,
//                isLenient = false,
//                serializeSpecialFloatingPointValues = false,
//                allowStructuredMapKeys = false,
//                prettyPrint = true,
//                unquotedPrint = false,
//                indent = "",
//                useArrayPolymorphism = false,
//                classDiscriminator = "",
//                updateMode = UpdateMode.OVERWRITE
//            )
//        )
//    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): OAuthApi {
        return retrofit.create(OAuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }


    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }
}
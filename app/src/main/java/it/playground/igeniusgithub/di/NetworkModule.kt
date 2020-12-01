package it.playground.igeniusgithub.di

import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.Navigator
import com.apollographql.apollo.ApolloClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import it.playground.igeniusgithub.BuildConfig
import it.playground.igeniusgithub.di.NetworkModule.BaseUrl.BASE_GRAPHQL_URL
import it.playground.igeniusgithub.di.NetworkModule.BaseUrl.BASE_LOGIN_POST_ACCESS_TOKEN
import it.playground.igeniusgithub.domain.network.OAuthApi
import it.playground.igeniusgithub.domain.network.OkHttpGraphInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    object BaseUrl {
        val BASE_GRAPHQL_URL = "https://api.github.com/graphql"
        val BASE_LOGIN_POST_ACCESS_TOKEN = "https://github.com/login/oauth/"

    }

    @Singleton
    @Provides
    fun provideJsonConfiguration(): Json {
        return Json {
            encodeDefaults = false
            ignoreUnknownKeys = true
            isLenient = false
            allowStructuredMapKeys = false
            prettyPrint = true
            useArrayPolymorphism = false
            classDiscriminator = ""

        }
    }


    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(json: Json, @Named("RestInstance") okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json"
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType.toMediaType()))
            .baseUrl(BASE_LOGIN_POST_ACCESS_TOKEN)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): OAuthApi {
        return retrofit.create(OAuthApi::class.java)
    }

    @Singleton
    @Named("RestInstance")
    @Provides
    fun provideRestOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Provides
    fun provideSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("AuthTokenSharedPref", Context.MODE_PRIVATE)

    }

    @Singleton
    @Named("GraphQLInstance")
    @Provides
    fun provideGraphQLOkHttpClient(interceptors: ArrayList<Interceptor>, preferences: SharedPreferences): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(OkHttpGraphInterceptor(preferences.getString("auth_token", "")!!, preferences)) //<- Ugly but necessary

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


    @Singleton
    @Provides
    fun provideApolloClient(@Named("GraphQLInstance") okHttpClient: OkHttpClient): ApolloClient{
        return ApolloClient.builder().okHttpClient(okHttpClient).serverUrl(BASE_GRAPHQL_URL).build()

    }

}
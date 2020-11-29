package it.playground.igeniusgithub.di

import android.content.Context
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import it.playground.igeniusgithub.BuildConfig
import it.playground.igeniusgithub.R
import javax.inject.Qualifier

@Module
object CoreModule {


    @Provides
    fun provideCoilImageLoader(context: Context): ImageLoader {
        return if(BuildConfig.DEBUG) {
            ImageLoader.Builder(context).logger(DebugLogger()).build()
        } else {
            ImageLoader.Builder(context).build()
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ClientId

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ClientSecret

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoginUrlTemplate


    @ClientId
    @Provides
    fun provideCLientIdString(context: Context): String {
        return context.getString(R.string.client_id)
    }

    @ClientSecret
    @Provides
    fun provideCLientSecretString(context: Context): String {
        return context.getString(R.string.client_secret)
    }

    @LoginUrlTemplate
    @Provides
    fun provideFullLoginUrlWithTemplateString(context: Context): String {
        return context.getString(R.string.login_url)
    }

}
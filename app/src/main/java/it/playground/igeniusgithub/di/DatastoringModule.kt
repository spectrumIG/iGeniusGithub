package it.playground.igeniusgithub.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import it.playground.igeniusgithub.di.CoreModule.ClientId
import it.playground.igeniusgithub.di.CoreModule.ClientSecret
import it.playground.igeniusgithub.di.CoreModule.LoginUrlTemplate
import it.playground.igeniusgithub.di.DatastoringModule.RepositoryBinds
import it.playground.igeniusgithub.di.DatastoringModule.UseCaseModule
import it.playground.igeniusgithub.domain.model.usecase.UseCase
import it.playground.igeniusgithub.domain.network.OAuthApi
import it.playground.igeniusgithub.domain.repository.DataSource
import it.playground.igeniusgithub.domain.repository.DefaultRepository
import it.playground.igeniusgithub.domain.repository.Repository
import it.playground.igeniusgithub.domain.repository.local.LocalDataSource
import it.playground.igeniusgithub.domain.repository.remote.RemoteDataSource
import it.playground.igeniusgithub.login.LoginUseCase
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [UseCaseModule::class, RepositoryBinds::class])
object DatastoringModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TokenRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TokenLocalDataSource

    @Singleton
    @TokenRemoteDataSource
    @Provides
    fun provideTasksRemoteDataSource(restapi: OAuthApi): DataSource {
        return RemoteDataSource(restapi)
    }

    @Singleton
    @TokenLocalDataSource
    @Provides
    fun provideTasksLocalDataSource(preferences: DataStore<Preferences>): DataSource {
        return LocalDataSource(preferences)
    }

    @Singleton
    @Provides
    fun providesPrefDataStoreSharedPref(context: Context): DataStore<Preferences> {
        return context.createDataStore(name = "GithubPref")
    }
    @Provides
    fun provideLoginUseCase(
        repository: Repository,
        @ClientId clientId: String,
        @ClientSecret clientSecret: String,
        @LoginUrlTemplate templateUrl: String
    ): UseCase {
        return LoginUseCase(repository,clientId,clientSecret,templateUrl)
    }

    @Module
    abstract class UseCaseModule {

        @Binds
        abstract fun bindLoginUseCase(loginUseCase: LoginUseCase): UseCase
    }

    @Module
    abstract class RepositoryBinds {

        @Singleton
        @Binds
        abstract fun bindRepository(repo: DefaultRepository): Repository

    }
}
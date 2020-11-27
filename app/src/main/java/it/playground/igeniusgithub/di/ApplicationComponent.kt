package it.playground.igeniusgithub.di


import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import it.playground.igeniusgithub.GithubApplication
import javax.inject.Singleton

/**
 *  Main application component for Dependency Injection mechanism provided by Dagger lib.
 *  It collects all the app modules
 *
 * */


@Component(modules = [CoreModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(application: GithubApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}

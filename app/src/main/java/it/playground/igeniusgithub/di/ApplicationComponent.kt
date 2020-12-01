package it.playground.igeniusgithub.di


import android.content.Context
import dagger.BindsInstance
import dagger.Component
import it.playground.igeniusgithub.GithubApplication
import it.playground.igeniusgithub.home.HomeFragment
import it.playground.igeniusgithub.login.LoginGithubActivity
import it.playground.igeniusgithub.search.SearchFragment
import javax.inject.Singleton

/**
 *  Main application component for Dependency Injection mechanism provided by Dagger lib.
 *  It collects all the app modules
 *
 * */


@Component(modules = [CoreModule::class, NetworkModule::class,DatastoringModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(application: GithubApplication)

    fun inject(activity: LoginGithubActivity)

    fun inject(homeFragment: HomeFragment)

    fun inject(searchFragment: SearchFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}

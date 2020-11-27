package it.playground.igeniusgithub

import android.app.Application
import it.playground.igeniusgithub.di.ApplicationComponent
import it.playground.igeniusgithub.di.DaggerApplicationComponent
import timber.log.Timber

class GithubApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

        }
    }
}
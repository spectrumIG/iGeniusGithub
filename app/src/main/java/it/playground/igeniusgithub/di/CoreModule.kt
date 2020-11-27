package it.playground.igeniusgithub.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import it.playground.igeniusgithub.BuildConfig
import javax.inject.Singleton

//@Module(
//    includes = [CoreModule.StartActivityModule::class, CoreModule.WelcomeModule::class, CoreModule.JoinModule::class,
//        CoreModule.LoginModule::class]
//)
@Module
object CoreModule {

    @Singleton
    @Provides
    fun providesEncriptedSharedPref(context: Context): DataStore<Preferences> {
        return context.createDataStore(name = "GithubPref")
    }

//    @Module
//    abstract class StartActivityModule {
//        @ContributesAndroidInjector
//        internal abstract fun startActivity(): StartActivity
//
//        @ContributesAndroidInjector
//        internal abstract fun splashFragment(): SplashFragment
//
//        @ContributesAndroidInjector
//        internal abstract fun permissionFragment(): PermissionRequestFragment
//    }
//
//    @Module
//    abstract class WelcomeModule {
//        @ContributesAndroidInjector
//        internal abstract fun welcomeFragment(): WelcomeFullscreenFragment
//    }
//
//    @Module
//    abstract class JoinModule {
//        @ContributesAndroidInjector
//        internal abstract fun joinFragment(): JoinConfFragment
//
//    }
//
//    @Module
//    abstract class LoginModule {
//        @ContributesAndroidInjector
//        internal abstract fun loginFragment(): LoginFragment
//    }

//    @Provides
//    fun provideCoilImageLoader(context: Context): ImageLoader {
//        return if(BuildConfig.DEBUG) {
//            ImageLoader.Builder(context).logger(DebugLogger()).build()
//        } else {
//            ImageLoader.Builder(context).build()
//        }
//    }

}
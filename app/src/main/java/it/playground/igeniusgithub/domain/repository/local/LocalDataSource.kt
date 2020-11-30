package it.playground.igeniusgithub.domain.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.DataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class LocalDataSource constructor(private val prefData: DataStore<Preferences>) : DataSource {
    private val _authTokenKey = preferencesKey<String>("auth_token")

    override suspend fun askForToken(clientId: String?, clientSecret: String?, code: String?): Result<String> {
        val result = prefData.data.map {
            val token = it[_authTokenKey] ?: ""
            if(token.isNotEmpty()) {
                Result.Success(token)
            } else {
                Result.Error(Exception("no suitable token"))
            }

        }
        return result.first()
    }

    override suspend fun saveTokenLocally(code: String) {
        prefData.storeValue(_authTokenKey, code)
    }
}

//TODO maybe use it after some refactoring
suspend inline fun <reified T> DataStore<Preferences>.getFromLocalStorage(
    PreferencesKey: Preferences.Key<T>, crossinline func: T.() -> Unit
) {
    data.catch {
        if(it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[PreferencesKey]
    }.collect {
        it?.let {
            func.invoke(it as T)
        }
    }
}

suspend inline fun <reified T> DataStore<Preferences>.storeValue(key: Preferences.Key<T>, value: Any) {
    edit {
        it[key] = value as T
    }
}



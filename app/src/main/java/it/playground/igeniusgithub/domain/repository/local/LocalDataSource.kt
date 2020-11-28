package it.playground.igeniusgithub.domain.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.DataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalDataSource constructor(private val prefData: DataStore<Preferences>) : DataSource {
    private val preferencesKey = preferencesKey<String>("auth_token")

    override suspend fun askForToken(clientId: String, clientSecret: String, code: String): Result<String> {
        val result = prefData.data.map {
            val token = it[preferencesKey]
            if(token.toString().isNotEmpty()) {
                Result.Success(token.toString())
            } else {
                Result.Error(Exception("no suitable token"))
            }

        }
        return result.first()
    }

    suspend fun saveTokenLocally(code: String) {
        prefData.edit { settings ->
            settings.set(preferencesKey,code)
        }
    }
}
package it.playground.igeniusgithub.domain.repository.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.DataSource

class LocalDataSource constructor(private val preferences: SharedPreferences) : DataSource {

    override suspend fun askForToken(clientId: String?, clientSecret: String?, code: String?): Result<String> {
        val token = preferences.getString("auth_token", "")
        return if(token.isNullOrEmpty()) {
            Result.Error(Exception("token invalid or not present"))
        } else {
            Result.Success(token)
        }
    }

    @SuppressLint("ApplySharedPref")
    override suspend fun saveTokenLocally(code: String) {
       preferences.edit().putString("auth_token",code).commit()
    }
}




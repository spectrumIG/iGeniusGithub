package it.playground.igeniusgithub.domain.repository

import it.playground.igeniusgithub.domain.model.Result

/**
 *  Type that a local or remote data source should implements
 * */

interface DataSource {
    suspend fun askForToken(clientId:String?,clientSecret:String?,code: String?): Result<String>
    suspend fun saveTokenLocally(code: String)
}

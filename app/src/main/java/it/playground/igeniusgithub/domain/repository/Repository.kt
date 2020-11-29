package it.playground.igeniusgithub.domain.repository

import it.playground.igeniusgithub.domain.model.Result

interface Repository {
    suspend fun retrieveTokenAndStoreIt(clientId:String,clientSecret:String,code: String): Result<String>
}
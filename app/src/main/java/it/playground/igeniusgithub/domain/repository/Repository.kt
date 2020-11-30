package it.playground.igeniusgithub.domain.repository

import com.apollographql.apollo.api.Response
import it.playground.igeniusgithub.ListAllUserRepoQuery
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun retrieveTokenAndStoreIt(clientId: String, clientSecret: String, code: String): Result<String>
    suspend fun retrieveUserRepositories(): Flow<Response<ListAllUserRepoQuery.Data>>
}
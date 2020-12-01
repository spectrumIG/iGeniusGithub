package it.playground.igeniusgithub.domain.repository

import com.apollographql.apollo.api.Response
import it.playground.igeniusgithub.ListAllUserRepoQuery
import it.playground.igeniusgithub.SearchForRepoQuery
import it.playground.igeniusgithub.SearchForUserQuery
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun retrieveTokenAndStoreIt(clientId: String, clientSecret: String, code: String): Result<String>
    suspend fun retrieveLoggedUserRepositories(): Flow<Response<ListAllUserRepoQuery.Data>>

    suspend fun searchForRepoByFilter(filter: String): Flow<Response<SearchForRepoQuery.Data>>

    @ExperimentalCoroutinesApi
    suspend fun searchForUserByFilter(filter: String): Flow<Response<SearchForUserQuery.Data>>
}
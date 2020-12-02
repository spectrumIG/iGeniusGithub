package it.playground.igeniusgithub.search

import it.playground.igeniusgithub.domain.model.usecase.UseCase
import it.playground.igeniusgithub.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val defaultRepository: Repository) : UseCase {

    override suspend fun invoke() {
        TODO("Not yet implemented")
    }


    @ExperimentalCoroutinesApi
    suspend fun searchUserBy(filter: String) {
        defaultRepository.searchForUserByFilter(filter).map { response ->
            Timber.i(
                "SEARCH ELEMENT (USER): ${
                    response.data?.search?.nodes?.forEach {
                        it?.asUser?.login
                    }
                }"
            )
        }.collect()
    }

    suspend fun searchRepoBy(filter: String) {
        defaultRepository.searchForRepoByFilter(filter).map { response ->
            Timber.i(
                "SEARCH ELEMENT (REPOSITORY) ${
                    response.data?.search?.nodes?.forEach {
                        it?.asRepository?.name
                    }
                }"
            )
        }.collect()
    }

}
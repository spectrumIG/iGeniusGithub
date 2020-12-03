package it.playground.igeniusgithub.search

import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.model.network.SearchRepository
import it.playground.igeniusgithub.domain.model.network.SearchUser
import it.playground.igeniusgithub.domain.model.usecase.UseCase
import it.playground.igeniusgithub.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val defaultRepository: Repository) : UseCase {


    @ExperimentalCoroutinesApi
    suspend fun searchUserBy(filter: String): Result<List<SearchUser>> {
        val result = ArrayList<SearchUser>()
        return try {

            defaultRepository.searchForUserByFilter(filter).map { response ->

                response.data?.search?.nodes?.forEach {
                    result.add(SearchUser(it?.asUser?.id, it?.asUser?.login, it?.asUser?.email, (it?.asUser?.avatarUrl as String), it.asUser.bio,
                        it.asUser.followers.totalCount, it.asUser.following.totalCount))
                }

            }.collect()

            Result.Success(result)
        } catch (exeption: Exception) {
            Result.Error(exeption)
        }
    }

    suspend fun searchRepoBy(filter: String): Result<List<SearchRepository>> {
        val result = ArrayList<SearchRepository>()
        return try {


            defaultRepository.searchForRepoByFilter(filter).map { response ->

                response.data?.search?.nodes?.forEach {
                    val languages = ArrayList<String>()

                    it?.asRepository?.languages?.nodes?.forEach { language -> languages.add(language!!.name) }

                    result.add(SearchRepository(it?.asRepository?.description, languages, it?.asRepository?.name, it?.asRepository?.stargazerCount,
                        it?.asRepository?.owner?.login, it?.asRepository?.owner?.avatarUrl as String?))

                }
            }.collect()

            Result.Success(result)
        } catch (exeption: Exception) {
            Result.Error(exeption)
        }

    }

}
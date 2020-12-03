package it.playground.igeniusgithub.home

import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.model.network.UserRepo
import it.playground.igeniusgithub.domain.model.usecase.UseCase
import it.playground.igeniusgithub.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val defaultRepository: Repository) : UseCase {


    suspend fun getAllUserRepo(): Result<List<UserRepo>> {
        val result = ArrayList<UserRepo>()
        return try {
            defaultRepository.retrieveLoggedUserRepositories().map {
                it.data?.viewer?.repositories?.nodes?.forEach { repo ->
                    val languages = ArrayList<String>()

                    repo?.languages?.nodes?.forEach { language -> languages.add(language!!.name) }

                    result.add(UserRepo(description = repo?.description, languages = languages, name = repo?.name,repo?.stargazerCount))
                }
            }.collect()
            Result.Success(result)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

}
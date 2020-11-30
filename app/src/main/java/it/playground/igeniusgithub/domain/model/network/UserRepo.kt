package it.playground.igeniusgithub.domain.model.network


import it.playground.igeniusgithub.domain.model.Mapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UserRepo(
    val description: String?,
    val languages: List<String>?,
    val name: String?,
    val starsNumber: Int?
)

data class UserRepoUI(
    val repositoryName: String,
    val languages: List<String>?,
    val description: String?,
    val starsNumber: Int
) : UiData

class UserRepoMapper: Mapper<UserRepo,UserRepoUI>{
    override fun mapFrom(from: UserRepo): UserRepoUI {
        return UserRepoUI(repositoryName = from.name!!,languages = from.languages,description = from.description,starsNumber = from.starsNumber ?: 0)
    }

}

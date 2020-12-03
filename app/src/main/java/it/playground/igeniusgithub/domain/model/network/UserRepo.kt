package it.playground.igeniusgithub.domain.model.network


import android.os.Parcelable
import it.playground.igeniusgithub.domain.model.Mapper
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UserRepo(
    val description: String?,
    val languages: List<String>?,
    val name: String?,
    val starsNumber: Int?
) : DomainModelData

@Parcelize
data class UserRepoUI(
    val repositoryName: String,
    val languages: String?,
    val description: String?,
    val starsNumber: Int
) : UiData, Parcelable

class UserRepoMapper: Mapper<UserRepo,UserRepoUI>{
    override fun mapFrom(from: UserRepo): UserRepoUI {
        return UserRepoUI(repositoryName = from.name!!,languages = from.languages?.joinToString(),description = from.description,starsNumber = from.starsNumber?: 0)
    }

}

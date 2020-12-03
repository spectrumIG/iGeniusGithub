package it.playground.igeniusgithub.domain.model.network

import android.os.Parcelable
import it.playground.igeniusgithub.domain.model.Mapper
import kotlinx.parcelize.Parcelize

data class SearchRepository(
    val description: String?,
    val languages: List<String>?,
    val name: String?,
    val starsNumber: Int?,
    val userLoginId: String?,
    val avatarUrl: String?
) : DomainModelData

@Parcelize
data class SearchRepositoryUI(
    val repositoryName: String,
    val languages: String?,
    val description: String?,
    val starsNumber: Int,
    val userLoginId: String?,
    val avatarUrl: String?
) : UiData, Parcelable

class SearchRepoMapper : Mapper<SearchRepository,SearchRepositoryUI> {
    override fun mapFrom(from: SearchRepository): SearchRepositoryUI{
        return SearchRepositoryUI(
            repositoryName = from.name!!,
            languages = from.languages?.joinToString(),
            description = from.description,
            starsNumber = from.starsNumber ?: 0,
            userLoginId = from.userLoginId,
            avatarUrl = from.avatarUrl
        )
    }
}

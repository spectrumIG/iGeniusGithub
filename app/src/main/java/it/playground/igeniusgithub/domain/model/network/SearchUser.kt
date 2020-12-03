package it.playground.igeniusgithub.domain.model.network

import it.playground.igeniusgithub.domain.model.Mapper

data class SearchUser(
    val id: String?,
    val name: String?,
    val email: String?,
    val avatarUrl: String?,
    val bio: String?,
    val totalFollower: Int?,
    val totalFollowing: Int?,

    ) : DomainModelData

data class SearchUserUI(
     val id: String?,
     val name: String?,
     val email: String?,
     val avatarUrl: String?,
     val bio: String?,
     val totalFollower: Int?,
     val totalFollowing: Int?,
     val isFromSearch: Boolean
): UiData

class SearchUserMapper : Mapper<SearchUser, SearchUserUI> {
    override fun mapFrom(from: SearchUser): SearchUserUI {
        return SearchUserUI(
            id = from.id, name = from.name, email = from.email, avatarUrl = from.avatarUrl, bio = from.bio, totalFollower = from
                .totalFollower,
            totalFollowing =
            from.totalFollowing, true
        )
    }

}
package it.playground.igeniusgithub.domain.model.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRepoDTO(
    @SerialName("description")
    val description: String?,
    @SerialName("languages")
    val languages: Languages?,
    @SerialName("name")
    val name: String?
) {
    @Serializable
    data class Languages(
        @SerialName("nodes")
        val nodes: List<Node?>?
    ) {
        @Serializable
        data class Node(
            @SerialName("name")
            val name: String?
        )
    }
}


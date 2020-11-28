package it.playground.igeniusgithub.domain.model.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizeResponse(
    @SerialName("access_token")
    val accessToken: String?,
    @SerialName("scope")
    val scope: String?,
    @SerialName("token_type")
    val tokenType: String?
) : NetworkModel
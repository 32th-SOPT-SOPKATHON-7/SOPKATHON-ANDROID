package com.solosol.a32th_sopt_sopkathon_7_android.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("commentId")
        val commentId: Int? = null,
        @SerialName("content")
        val content: String? = null,
        @SerialName("createdAt")
        val createdAt: String? = null,
        @SerialName("updatedAt")
        val updatedAt: String? = null,
        @SerialName("postId")
        val postId: Int? = null
    )
}
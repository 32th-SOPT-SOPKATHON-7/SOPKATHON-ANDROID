package com.solosol.a32th_sopt_sopkathon_7_android.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailArticleResponse(
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
        @SerialName("comments")
        val comments: List<Comment?>? = null,
        @SerialName("content")
        val content: String? = null,
        @SerialName("createdAt")
        val createdAt: String? = null,
        @SerialName("likeCnt")
        val likeCnt: Int? = null,
        @SerialName("postId")
        val postId: Int? = null,
        @SerialName("stationId")
        val stationId: Int? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("updateAt")
        val updateAt: String? = null
    ) {
        @Serializable
        data class Comment(
            @SerialName("commentId")
            val commentId: Int? = null,
            @SerialName("content")
            val content: String? = null,
            @SerialName("createdAt")
            val createdAt: String? = null
        )
    }
}
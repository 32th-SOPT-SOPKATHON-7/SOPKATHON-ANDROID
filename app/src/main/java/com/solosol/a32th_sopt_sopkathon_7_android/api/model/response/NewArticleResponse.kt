package com.solosol.a32th_sopt_sopkathon_7_android.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewArticleResponse(
    @SerialName("data")
    val data: List<Data?>? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("postCommentCnt")
        val postCommentCnt: Int? = null,
        @SerialName("postContent")
        val postContent: String? = null,
        @SerialName("postCreatedAt")
        val postCreatedAt: String? = null,
        @SerialName("postId")
        val postId: Int? = null,
        @SerialName("postLikeCnt")
        val postLikeCnt: Int? = null,
        @SerialName("postTitle")
        val postTitle: String? = null
    )
}
package com.solosol.a32th_sopt_sopkathon_7_android

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostListDto(
    @SerialName("data")
    val data: List<PostingData>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean
) {
    @Serializable
    data class PostingData(
        @SerialName("postCommentCnt")
        val postCommentCnt: Int,
        @SerialName("postContent")
        val postContent: String,
        @SerialName("postCreatedAt")
        val postCreatedAt: String,
        @SerialName("postId")
        val postId: Int,
        @SerialName("postLikeCnt")
        val postLikeCnt: Int,
        @SerialName("postTitle")
        val postTitle: String
    )
}
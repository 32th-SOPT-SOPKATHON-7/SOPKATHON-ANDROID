package com.solosol.a32th_sopt_sopkathon_7_android.api.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequest(
    @SerialName("content")
    val content: String? = null
)
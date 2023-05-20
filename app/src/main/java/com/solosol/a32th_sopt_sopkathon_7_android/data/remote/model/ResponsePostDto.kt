package com.solosol.a32th_sopt_sopkathon_7_android.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String
)
package com.solosol.a32th_sopt_sopkathon_7_android

data class ResponsePostDto(
    val data: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
)
package com.solosol.a32th_sopt_sopkathon_7_android.data.remote.service

import com.solosol.a32th_sopt_sopkathon_7_android.data.remote.model.RequestPostDto
import com.solosol.a32th_sopt_sopkathon_7_android.data.remote.model.ResponsePostDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {
    @POST("/post")
    fun post(
        @Body requestPostDto: RequestPostDto
    ): Call<ResponsePostDto>
}
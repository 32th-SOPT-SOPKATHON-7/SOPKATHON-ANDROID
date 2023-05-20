package com.solosol.a32th_sopt_sopkathon_7_android.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentService {
    @POST("comment")
    fun getComment(@Body request: RequestCommentDto): Call<ResponseCommentDto>
}
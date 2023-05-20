package com.solosol.a32th_sopt_sopkathon_7_android.data.remote.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.solosol.a32th_sopt_sopkathon_7_android.data.remote.service.PostService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object PostApiFactory {
    private const val BASE_URL = "BuildConfig.BASE_URL"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object PostServicePool {
    val PostService = PostApiFactory.create<PostService>()
}
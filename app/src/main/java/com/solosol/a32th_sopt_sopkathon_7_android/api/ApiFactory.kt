package com.solosol.a32th_sopt_sopkathon_7_android.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tbuonomo.viewpagerdotsindicator.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object ApiFactory {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://13.125.255.32:8080")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)

    val soptService:SoptService = retrofit.create()

}
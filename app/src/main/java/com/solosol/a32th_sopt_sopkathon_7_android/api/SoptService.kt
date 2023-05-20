package com.solosol.a32th_sopt_sopkathon_7_android.api

import com.solosol.a32th_sopt_sopkathon_7_android.api.model.request.CreateArticleRequest
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.request.CreateCommentRequest
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.DetailArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.NewArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.CreateArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TotalStationStationResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TrendArticleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SoptService {
    @GET("/api/station/total")
    suspend fun getAllStationLove(): Response<TotalStationStationResponse>

    @GET("/api/posts/trend")
    suspend fun getTrendList(
        @Query("stationName") stationName:String
    ):Response<TrendArticleResponse>

    @GET("/api/posts/hot")
    suspend fun getNewList(
        @Query("stationName") stationName:String
    ):Response<NewArticleResponse>

    @POST("/api/posts/create")
    suspend fun postCreateArticle(
        @Body body: CreateArticleRequest
    ): Response<CreateArticleResponse>

    @GET("/api/posts/detail/{postId}")
    suspend fun getDetailArticle(
        @Path("postId") postId:Int
    ):Response<DetailArticleResponse>

    @POST("/api/comments/create/{postId}")
    suspend fun postCreateComment(
        @Path("postId") postId: Int,
        @Body body: CreateCommentRequest
    ): Response<CreateCommentRequest>
}
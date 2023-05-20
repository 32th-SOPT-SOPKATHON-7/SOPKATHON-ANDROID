package com.solosol.a32th_sopt_sopkathon_7_android.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalStationStationResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("hotPost")
        val hotPost: HotPost? = null,
        @SerialName("stationLikeList")
        val stationLikeList: List<StationLikeList?>? = null
    ) {
        @Serializable
        data class HotPost(
            @SerialName("createdAt")
            val createdAt: String? = null,
            @SerialName("title")
            val title: String? = null
        )

        @Serializable
        data class StationLikeList(
            @SerialName("stationName")
            val stationName:String? = null,
            val totalLikeCnt:Int? = null
        )
    }
}
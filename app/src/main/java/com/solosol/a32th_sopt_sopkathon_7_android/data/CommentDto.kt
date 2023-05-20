package com.solosol.a32th_sopt_sopkathon_7_android.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/*이거 필요한가.. 잘 모르겠,,*/
@Serializable
data class RequestCommentDto(
    @SerialName("id") val id: String, @SerialName("comment") val Comment: String
)

@Serializable
data class ResponseCommentDto(
    @SerialName("time") val time: String,
    @SerialName("data") val data: CommentInfo,
) {
    @Serializable
    data class CommentInfo(
        @SerialName("id") val id: String, @SerialName("comment") val Comment: String
    )
}
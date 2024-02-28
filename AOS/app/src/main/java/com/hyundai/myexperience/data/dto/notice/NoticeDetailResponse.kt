package com.hyundai.myexperience.data.dto.notice


import kotlinx.serialization.Serializable

@Serializable
data class NoticeDetailResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val content: String,
        val createdAt: String,
        val id: Int,
        val imageName: String,
        val imageUrl: String,
        val title: String
    )
}
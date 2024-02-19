package com.hyundai.myexperience.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NoticeListResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val hasNext: Boolean,
        val values: List<Value>
    ) {
        @Serializable
        data class Value(
            val content: String,
            val id: Int,
            val imageName: String,
            val imageUrl: String,
            val title: String
        )
    }
}
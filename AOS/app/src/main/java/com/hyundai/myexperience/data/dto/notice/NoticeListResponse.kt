package com.hyundai.myexperience.data.dto.notice

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
            val createdAt: String,
            val id: Int,
            val title: String
        )
    }
}
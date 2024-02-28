package com.hyundai.myexperience.data.dto.schedule


import kotlinx.serialization.Serializable

@Serializable
data class ScheduleListResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val hasNext: Boolean,
        val values: List<String>
    )
}
package com.hyundai.myexperience.data.dto.schedule


import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDetailResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    @Serializable
    data class Result(
        val key: String,
        val list: List<String>
    )
}
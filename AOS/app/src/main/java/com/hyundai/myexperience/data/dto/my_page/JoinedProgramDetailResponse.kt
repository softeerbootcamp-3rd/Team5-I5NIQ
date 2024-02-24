package com.hyundai.myexperience.data.dto.my_page


import kotlinx.serialization.Serializable

@Serializable
data class JoinedProgramDetailResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val carName: String,
        val participants: Int,
        val participationId: Int,
        val programCategory: String,
        val programLevel: String,
        val programName: String,
        val startDateTime: String
    )
}
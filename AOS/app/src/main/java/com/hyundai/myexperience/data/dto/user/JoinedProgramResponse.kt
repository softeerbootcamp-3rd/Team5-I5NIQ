package com.hyundai.myexperience.data.dto.user


import kotlinx.serialization.Serializable

@Serializable
data class JoinedProgramResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    @Serializable
    data class Result(
        val category: String,
        val dateTime: String,
        val level: String,
        val participationId: Long,
        val programName: String,
        val status: String
    )
}
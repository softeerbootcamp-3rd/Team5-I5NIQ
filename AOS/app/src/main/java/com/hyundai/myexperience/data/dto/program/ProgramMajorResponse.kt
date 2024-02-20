package com.hyundai.myexperience.data.dto.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramMajorResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val estimatedDuration: String,
        val levelName: String,
        val maxMemberNumber: Int,
        val programCars: List<String>,
        val programCategoryDescription: String,
        val programCategoryName: String,
        val programDescription: String,
        val programId: Int,
        val qualification: String
    )
}
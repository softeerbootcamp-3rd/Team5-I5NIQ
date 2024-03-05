package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationProgramByCarResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    @Serializable
    data class Result(
        val programDetailList: List<ProgramDetail>,
        val programName: String
    ) {
        @Serializable
        data class ProgramDetail(
            val category: String,
            val classDetailList: List<ClassDetail>,
            val level: String,
            val programId: Int
        ) {
            @Serializable
            data class ClassDetail(
                val monthDate: String,
                val status: String
            )
        }
    }
}
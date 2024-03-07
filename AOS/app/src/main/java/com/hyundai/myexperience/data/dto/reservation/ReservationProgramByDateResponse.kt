package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationProgramByDateResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    @Serializable
    data class Result(
        val key: String,
        val list: List<Company>
    ) {
        @Serializable
        data class Company(
            val key: String,
            val list: List<Program>
        ) {
            @Serializable
            data class Program(
                val programId: Int,
                val programLevel: String,
                val reservationStatus: String
            )
        }
    }
}
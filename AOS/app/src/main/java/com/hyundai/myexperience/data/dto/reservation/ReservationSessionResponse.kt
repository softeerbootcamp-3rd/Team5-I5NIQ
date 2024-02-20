package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationSessionResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val carName: String,
        val classes: List<Class>,
        val companyName: String,
        val programName: String,
        val reservationDate: String
    ) {
        @Serializable
        data class Class(
            val canReservation: Boolean,
            val classId: Int,
            val cost: Int,
            val participationCount: Int,
            val participationOccupancy: Int,
            val reservationDateTime: String
        )
    }
}
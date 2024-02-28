package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationCarDateResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val selectMenus: List<SelectMenu>
    ) {
        @Serializable
        data class SelectMenu(
            val carName: String,
            val programDates: List<ProgramDate>
        ) {
            @Serializable
            data class ProgramDate(
                val canReservation: Boolean,
                val carId: Int,
                val programId: Int,
                val reservationDate: String
            )
        }
    }
}
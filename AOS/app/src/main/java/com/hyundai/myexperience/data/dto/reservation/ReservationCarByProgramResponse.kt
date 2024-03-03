package com.hyundai.myexperience.data.dto.reservation

data class ReservationCarByProgramResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    data class Result(
        val carStatusList: List<Car>,
        val programId: Int,
        val startDate: String
    ) {
        data class Car(
            val carName: String,
            val status: String
        )
    }
}
package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationDateResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    @Serializable
    data class Result(
        val key: String,
        val value: String
    )
}
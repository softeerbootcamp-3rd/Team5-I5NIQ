package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String?,
    val result: Boolean
)
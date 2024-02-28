package com.hyundai.myexperience.data.entity.reservation

data class ReservationDate (
    val date: String,
    val id: Int,
    val status: String,
    val cost: Int = 0,
    val maxCount: Int = 0
)
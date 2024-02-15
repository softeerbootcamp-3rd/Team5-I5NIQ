package com.hyundai.myexperience.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String,
)
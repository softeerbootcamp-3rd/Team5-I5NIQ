package com.hyundai.myexperience.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)
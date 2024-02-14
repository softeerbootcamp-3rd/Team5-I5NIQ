package com.hyundai.myexperience.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest (
    val id: String,
    val password: String
)
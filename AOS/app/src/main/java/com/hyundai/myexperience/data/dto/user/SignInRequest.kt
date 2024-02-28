package com.hyundai.myexperience.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest (
    val id: String,
    val password: String
)
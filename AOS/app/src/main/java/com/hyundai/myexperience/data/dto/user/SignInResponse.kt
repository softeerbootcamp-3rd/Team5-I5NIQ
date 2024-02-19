package com.hyundai.myexperience.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val message: String,
)
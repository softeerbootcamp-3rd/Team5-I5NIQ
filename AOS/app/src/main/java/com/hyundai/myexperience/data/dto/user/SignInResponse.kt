package com.hyundai.myexperience.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val isSuccess: Boolean,
    val message: String,
    val code: String,
    val result: Boolean
)
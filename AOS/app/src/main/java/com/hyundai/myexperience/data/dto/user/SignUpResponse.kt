package com.hyundai.myexperience.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val isSuccess: Boolean,
    val message: String,
    val code: String,
    val result: Boolean
)
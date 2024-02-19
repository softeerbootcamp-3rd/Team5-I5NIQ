package com.hyundai.myexperience.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest (
    val id: String,
    val name: String,
    val password: String
)
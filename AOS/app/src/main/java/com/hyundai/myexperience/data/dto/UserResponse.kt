package com.hyundai.myexperience.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val username: String
)
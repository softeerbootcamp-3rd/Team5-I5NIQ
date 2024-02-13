package com.hyundai.myexperience.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val message: String,
    val userInfo: UserInfo
) {
    @Serializable
    data class UserInfo(
        val username: String
    )
}
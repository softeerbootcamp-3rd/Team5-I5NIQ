package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.UserResponse
import com.hyundai.myexperience.data.entity.User

fun UserResponse.UserInfo.mapToUser(): User {
    return User(
        username = username
    )
}
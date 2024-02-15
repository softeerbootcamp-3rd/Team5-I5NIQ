package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.LoginResponse
import com.hyundai.myexperience.data.entity.User

fun LoginResponse.mapToUser(): User {
    return User(
        message = message
    )
}
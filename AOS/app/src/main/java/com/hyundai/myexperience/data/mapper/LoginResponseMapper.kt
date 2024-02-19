package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.user.SignInResponse
import com.hyundai.myexperience.data.entity.User

fun SignInResponse.mapToUser(): User {
    return User(
        message = message
    )
}
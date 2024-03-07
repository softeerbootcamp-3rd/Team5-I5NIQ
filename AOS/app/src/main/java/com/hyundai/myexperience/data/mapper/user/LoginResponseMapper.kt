package com.hyundai.myexperience.data.mapper.user

import com.hyundai.myexperience.data.dto.user.SignResponse
import com.hyundai.myexperience.data.entity.user.User

fun SignResponse.mapToUser(): User {
    return User(
        message = message
    )
}
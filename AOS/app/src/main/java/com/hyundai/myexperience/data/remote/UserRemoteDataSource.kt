package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.remote.service.UserService

class UserRemoteDataSource(private val service: UserService) {
    suspend fun requestUsername(url: String): String? {
        return service.requestUserInfo(url).body()?.username
    }
}
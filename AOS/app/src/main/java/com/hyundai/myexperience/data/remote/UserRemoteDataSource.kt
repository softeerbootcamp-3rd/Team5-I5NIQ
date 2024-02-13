package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.dto.UserResponse
import com.hyundai.myexperience.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: UserService) {
    suspend fun requestUser(url: String): UserResponse.UserInfo? {
        return service.requestUserInfo(url).body()?.userInfo
    }
}
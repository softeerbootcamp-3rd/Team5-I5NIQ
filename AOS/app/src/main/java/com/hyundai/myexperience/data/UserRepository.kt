package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.User
import com.hyundai.myexperience.data.mapper.mapToUser
import com.hyundai.myexperience.data.remote.UserRemoteDataSource

class UserRepository(
    private val slideRemoteDataSource: UserRemoteDataSource
) {
    suspend fun requestUsername(url: String): User? {
        val user = slideRemoteDataSource.requestUser(url)?.mapToUser()

        return user
    }
}
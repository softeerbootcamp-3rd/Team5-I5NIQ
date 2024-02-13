package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.User
import com.hyundai.myexperience.data.mapper.mapToUser
import com.hyundai.myexperience.data.remote.UserRemoteDataSource

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun requestUsername(url: String): User? {
        val user = userRemoteDataSource.requestUser(url)?.mapToUser()

        return user
    }
}
package com.hyundai.myexperience.data.repository

import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import com.hyundai.myexperience.data.entity.user.JoinedProgramDetail
import com.hyundai.myexperience.data.entity.user.JoinedProgramItem
import com.hyundai.myexperience.data.entity.user.MyPage
import com.hyundai.myexperience.data.local.UserLocalDataSource
import com.hyundai.myexperience.data.mapper.user.mapToJoinedDetail
import com.hyundai.myexperience.data.mapper.user.mapToJoinedProgramList
import com.hyundai.myexperience.data.mapper.user.mapToMyPage
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.data_source.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun requestSignIn(id: String, password: String): Boolean {
        return userRemoteDataSource.requestSignIn(SignInRequest(id, password))
    }

    suspend fun requestSignUp(id: String, name: String, password: String): Boolean {
        return userRemoteDataSource.requestSignUp(SignUpRequest(id, name, password))
    }

    suspend fun requestSignOut(): Boolean {
        return userRemoteDataSource.requestSignOut()
    }

    suspend fun setIsSigned(isSigned: Boolean) {
        userLocalDataSource.setIsSigned(isSigned)
    }

    suspend fun getIsSigned(): Boolean {
        return userLocalDataSource.getIsSigned()
    }

    suspend fun setCookie(cookie: String) {
        userLocalDataSource.setCookie(cookie)
    }

    suspend fun getCookie(): String {
        return userLocalDataSource.getCookie()
    }

    fun setCookieToConnection(cookie: String) {
        ServerConnection.setCookie(cookie)
    }

    suspend fun requestMypage(): MyPage? {
        return userRemoteDataSource.requestMyPage()?.mapToMyPage()
    }

    suspend fun requestJoinedPrograms(status: String): List<JoinedProgramItem>? {
        return userRemoteDataSource.requestJoinedPrograms(status)?.mapToJoinedProgramList()
    }

    suspend fun requestJoinedProgramDetail(id: Long): JoinedProgramDetail? {
        return userRemoteDataSource.requestJoinedProgramDetail(id)?.mapToJoinedDetail()
    }
}
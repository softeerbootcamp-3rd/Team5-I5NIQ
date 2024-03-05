package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.user.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.dto.user.JoinedProgramResponse
import com.hyundai.myexperience.data.dto.user.MyPageResponse
import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignResponse
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @POST("user/login")
    suspend fun requestSignIn(@Body signInRequest: SignInRequest): Response<SignResponse>

    @POST("user/join")
    suspend fun requestSignUp(@Body signUpRequest: SignUpRequest): Response<SignResponse>

    @POST("user/logout")
    suspend fun requestSignOut(): Response<SignResponse>

    @GET("user/mypage")
    suspend fun requestMyPage() : Response<MyPageResponse>

    @GET("/user/programs")
    suspend fun requestJoinedPrograms(@Query("status") status: String): Response<JoinedProgramResponse>

    @GET("/user/participations/{participationId}")
    suspend fun requestJoinedProgramDetail(@Path("participationId") id: Long): Response<JoinedProgramDetailResponse>
}
package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.program.ProgramCommentResponse
import com.hyundai.myexperience.data.dto.program.ProgramConfResponse
import com.hyundai.myexperience.data.dto.program.ProgramMajorResponse
import com.hyundai.myexperience.data.dto.program.ProgramTrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProgramService {
    @GET("program/information")
    suspend fun requestProgramMajorData(@Query("program-id") programId: Int): Response<ProgramMajorResponse>

    @GET("program/detail")
    suspend fun requestProgramDetailData(@Query("program-id") programId: Int): Response<ProgramConfResponse>

    @GET("program/location")
    suspend fun requestProgramTrackData(@Query("program-id") programId: Int): Response<ProgramTrackResponse>

    @GET("program/comments")
    suspend fun requestProgramCommentData(@Query("program-id") programId: Int): Response<ProgramCommentResponse>
}
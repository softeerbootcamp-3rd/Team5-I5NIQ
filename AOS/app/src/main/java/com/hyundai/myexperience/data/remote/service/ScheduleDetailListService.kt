package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleDetailListService {
    @GET("schedule/DRIVING_EXPERIENCE/{date}")
    suspend fun responseExperienceScheduleDetailList(@Path("date") date: String): Response<ScheduleDetailListResponse>

    @GET("schedule/DRIVING_PLEASURE/{date}")
    suspend fun responsePleasureScheduleDetailList(@Path("date") date: String): Response<ScheduleDetailListResponse>
}
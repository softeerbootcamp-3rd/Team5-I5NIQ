package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ScheduleService {
    @GET("schedule/DRIVING_EXPERIENCE")
    suspend fun requestExperienceSchedules(): Response<ScheduleListResponse>

    @GET("schedule/DRIVING_PLEASURE")
    suspend fun requestPleasureSchedules(): Response<ScheduleListResponse>
}
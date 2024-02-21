package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailListResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.ScheduleDetailListService
import javax.inject.Inject

class ScheduleDetailListRemoteDataSource @Inject constructor(private val service: ScheduleDetailListService) {
    suspend fun responsePleasureScheduleDetailList(date: String): ScheduleDetailListResponse? {
        val response = service.responsePleasureScheduleDetailList(date)

        if (response.isSuccessful) {
            Log.d("response", "success")
            return response.body()
        }

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }

    suspend fun responseExperienceScheduleDetailList(date: String): ScheduleDetailListResponse? {
        val response = service.responseExperienceScheduleDetailList(date)

        if (response.isSuccessful) {
            Log.d("response", "success")
            return response.body()
        }

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }
}
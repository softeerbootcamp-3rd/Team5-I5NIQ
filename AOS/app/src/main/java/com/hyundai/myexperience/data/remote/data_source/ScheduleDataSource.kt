package com.hyundai.myexperience.data.remote.data_source

import android.util.Log
import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailResponse
import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.ScheduleService
import javax.inject.Inject

class ScheduleDataSource @Inject constructor(
    private val service: ScheduleService
) {
    suspend fun requestSchedules(program: String): ScheduleListResponse? {
        val response = service.requestSchedules(program)

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }

    suspend fun requestScheduleDetail(program: String, date: String): List<ScheduleDetailResponse.Result>? {
        val response = service.requestScheduleDetail(program, date)
        Log.d("check_response", "${response.isSuccessful} $program $date")

        if (response.isSuccessful) return response.body()?.result

        return null
    }
}
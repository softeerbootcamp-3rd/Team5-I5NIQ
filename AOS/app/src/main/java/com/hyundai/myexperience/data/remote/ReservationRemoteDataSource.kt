package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.remote.service.ReservationService
import javax.inject.Inject

class ReservationRemoteDataSource @Inject constructor(private val service: ReservationService) {
    suspend fun requestPrograms(): List<ReservationProgramResponse.Result.Program>? {
        val response = service.requestPrograms()
        Log.d("check_response", response.isSuccessful.toString())

        if (response.isSuccessful) return response.body()?.result?.programs

        return null
    }
}
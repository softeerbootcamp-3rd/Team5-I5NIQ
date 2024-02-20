package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.reservation.ReservationCarDateResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.remote.service.ReservationService
import javax.inject.Inject

class ReservationRemoteDataSource @Inject constructor(private val service: ReservationService) {
    suspend fun requestPrograms(): List<ReservationProgramResponse.Result.Program>? {
        val response = service.requestPrograms()

        if (response.isSuccessful) return response.body()?.result?.programs

        return null
    }

    suspend fun requestCarDates(id: Int): List<ReservationCarDateResponse.Result.SelectMenu>? {
        val response = service.requestCarDates(id)

        if (response.isSuccessful) return response.body()?.result?.selectMenus

        return null
    }
}
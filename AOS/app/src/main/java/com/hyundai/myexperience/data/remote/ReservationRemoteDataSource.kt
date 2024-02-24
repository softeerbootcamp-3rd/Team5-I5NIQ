package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.dto.reservation.ReservationCarDateResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationSessionResponse
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

    suspend fun requestSessions(
        programId: Int,
        carId: Int,
        date: String
    ): List<ReservationSessionResponse.Result.Class>? {
        val response = service.requestSessions(programId, carId, date)

        if (response.isSuccessful) return response.body()?.result?.classes

        return null
    }
}
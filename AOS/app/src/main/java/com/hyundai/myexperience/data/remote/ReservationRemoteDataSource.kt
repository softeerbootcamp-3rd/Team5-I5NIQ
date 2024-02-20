package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.remote.service.ReservationService
import javax.inject.Inject

class ReservationRemoteDataSource @Inject constructor(private val service: ReservationService) {
    suspend fun requestPrograms(): List<ReservationProgramResponse.Result.CompanyProgram>? {
        val response = service.requestPrograms()

        if (response.isSuccessful) return response.body()?.result?.companyPrograms

        return null
    }
}
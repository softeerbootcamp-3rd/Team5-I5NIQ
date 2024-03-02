package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.reservation.ReservationCarDateResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationDateResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationSessionResponse
import com.hyundai.myexperience.data.dto.reservation.ReservationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReservationService {
    @GET("reservation/step1/program")
    suspend fun requestPrograms(): Response<ReservationProgramResponse>

    @GET("reservation/step2/program")
    suspend fun requestCarDates(
        @Query("program-id") programId: Int
    ): Response<ReservationCarDateResponse>

    @GET("reservation/step1/date")
    suspend fun requestDates(): Response<ReservationDateResponse>

    @GET("reservation/step3")
    suspend fun requestSessions(
        @Query("program-id") programId: Int,
        @Query("car-id") carId: Int,
        @Query("reservation-date") reservationDate: String
    ): Response<ReservationSessionResponse>

    @POST("reservation")
    suspend fun requestReservation(
        @Query("class-id") classId: Int,
        @Query("amount") amount: Int,
    ): Response<ReservationResponse>
}
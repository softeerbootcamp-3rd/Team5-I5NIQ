package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.data.entity.reservation.ReservationDate
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import com.hyundai.myexperience.data.mapper.mapToReservationDatesItem
import com.hyundai.myexperience.data.mapper.mapToLevelsItem
import com.hyundai.myexperience.data.mapper.mapToReservationDateItem
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import javax.inject.Inject

class ReservationRepository @Inject constructor(
    private val reservationRemoteDataSource: ReservationRemoteDataSource
) {
    suspend fun requestExperiencePrograms(): List<LevelsItem>? {
        return reservationRemoteDataSource.requestPrograms()?.get(0)?.companyPrograms?.map { it.mapToLevelsItem() }
    }

    suspend fun requestPleasurePrograms(): List<LevelsItem>? {
        return reservationRemoteDataSource.requestPrograms()?.get(1)?.companyPrograms?.map { it.mapToLevelsItem() }
    }

    suspend fun requestCarDates(id: Int): List<ReservationDatesItem>? {
        return reservationRemoteDataSource.requestCarDates(id)?.map { it.mapToReservationDatesItem() }
    }

    suspend fun requestSessions(programId: Int, carId: Int, date: String): List<ReservationDate>? {
        return reservationRemoteDataSource.requestSessions(programId, carId, date)?.map { it.mapToReservationDateItem() }
    }
}
package com.hyundai.myexperience.data.repository

import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.data.entity.reservation.ReservationCar
import com.hyundai.myexperience.data.entity.reservation.ReservationDate
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import com.hyundai.myexperience.data.mapper.reservation.mapToLevelsItem
import com.hyundai.myexperience.data.mapper.reservation.mapToReservationCar
import com.hyundai.myexperience.data.mapper.reservation.mapToReservationDate
import com.hyundai.myexperience.data.mapper.reservation.mapToReservationDatesItem
import com.hyundai.myexperience.data.remote.data_source.ReservationQueueDataSource
import com.hyundai.myexperience.data.remote.data_source.ReservationRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReservationRepository @Inject constructor(
    private val reservationRemoteDataSource: ReservationRemoteDataSource,
    private val reservationQueueDataSource: ReservationQueueDataSource
) {
    suspend fun requestExperiencePrograms(): List<LevelsItem>? {
        return reservationRemoteDataSource.requestPrograms()
            ?.get(0)?.companyPrograms?.map { it.mapToLevelsItem() }
    }

    suspend fun requestPleasurePrograms(): List<LevelsItem>? {
        return reservationRemoteDataSource.requestPrograms()
            ?.get(1)?.companyPrograms?.map { it.mapToLevelsItem() }
    }

    suspend fun requestExperienceProgramsByDate(date: String): List<LevelsItem>? {
        return reservationRemoteDataSource.requestProgramsByDate(date)
            ?.get(0)?.list?.map { it.mapToLevelsItem() }
    }

    suspend fun requestPleasureProgramsByDate(date: String): List<LevelsItem>? {
        return reservationRemoteDataSource.requestProgramsByDate(date)
            ?.get(1)?.list?.map { it.mapToLevelsItem() }
    }

    suspend fun requestCarDates(id: Int): List<ReservationDatesItem>? {
        return reservationRemoteDataSource.requestCarDates(id)
            ?.map { it.mapToReservationDatesItem() }
    }

    suspend fun requestDates(): List<ReservationDate>? {
        return reservationRemoteDataSource.requestDates()?.map { it.mapToReservationDate() }
    }

    suspend fun requestCarsByProgram() {
    }

    suspend fun requestCars(): List<ReservationCar>? {
        return reservationRemoteDataSource.requestCars()?.map { it.mapToReservationCar() }
    }

    suspend fun requestProgramsByCar(carId: Int) {
//        return reservationRemoteDataSource.requestProgramsByCar(carId)
    }

    suspend fun requestSessions(programId: Int, carId: Int, date: String): List<ReservationDate>? {
        return reservationRemoteDataSource.requestSessions(programId, carId, date)
            ?.map { it.mapToReservationDate() }
    }

    suspend fun requestReservation(classId: Int, amount: Int): Boolean {
        return reservationRemoteDataSource.requestReservation(classId, amount)
    }

    suspend fun initConnection() {
        reservationQueueDataSource.connect()
    }

    fun receiveData(): Flow<String> {
        return reservationQueueDataSource.receiveData()
    }

    suspend fun closeConnection() {
        reservationQueueDataSource.disconnect()
    }
}
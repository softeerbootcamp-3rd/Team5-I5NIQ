package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.LevelsItem
import com.hyundai.myexperience.data.mapper.toLevelsItem
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import javax.inject.Inject

class ReservationRepository @Inject constructor(
    private val reservationRemoteDataSource: ReservationRemoteDataSource
) {
    suspend fun requestExperiencePrograms(): List<LevelsItem>? {
        return reservationRemoteDataSource.requestPrograms()?.get(0)?.companyPrograms?.map { it.toLevelsItem() }
    }

    suspend fun requestPleasurePrograms(): List<LevelsItem>? {
        return reservationRemoteDataSource.requestPrograms()?.get(1)?.companyPrograms?.map { it.toLevelsItem() }
    }
}
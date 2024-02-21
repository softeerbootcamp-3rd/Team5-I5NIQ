package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.entity.ScheduleDetailsItem
import com.hyundai.myexperience.data.mapper.mapToNoticeList
import com.hyundai.myexperience.data.mapper.mapToScheduleDetailList
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import com.hyundai.myexperience.data.remote.ScheduleDetailListRemoteDataSource
import javax.inject.Inject

class ScheduleDetailListRepository @Inject constructor(
    private val ScheduleDetailListRemoteDataSource: ScheduleDetailListRemoteDataSource
) {
    suspend fun responsePleasure(date: String): List<ScheduleDetailsItem>? {
        val details = ScheduleDetailListRemoteDataSource.responsePleasureScheduleDetailList(date)?.mapToScheduleDetailList()

        return details?.scheduleDetails
    }

    suspend fun responseExperience(date: String): List<ScheduleDetailsItem>? {
        val details = ScheduleDetailListRemoteDataSource.responseExperienceScheduleDetailList(date)?.mapToScheduleDetailList()

        return details?.scheduleDetails
    }
}
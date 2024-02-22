package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.mapper.mapToNoticeList
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import javax.inject.Inject

class NoticeListRepository @Inject constructor(
    private val noticeListRemoteDataSource: NoticeListRemoteDataSource
) {
    suspend fun response(): List<NoticesItem>? {
        val notices = noticeListRemoteDataSource.requestNoticeList()?.mapToNoticeList()

        return notices?.notices
    }
}
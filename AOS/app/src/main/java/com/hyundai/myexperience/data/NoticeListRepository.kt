package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.dto.NoticeListResponse
import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.mapper.mapToNoticeList
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import javax.inject.Inject

class NoticeListRepository @Inject constructor(
    private val noticeListRemoteDataSource: NoticeListRemoteDataSource
) {
    suspend fun response(): List<NoticesItem>? {
        val notices = noticeListRemoteDataSource.responseNoticeList()?.mapToNoticeList()

        return notices?.notices
    }
}
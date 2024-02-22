package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.mapper.mapToNoticeList
import com.hyundai.myexperience.data.mapper.mapToNoticesItem
import com.hyundai.myexperience.data.remote.NoticeRemoteDataSource
import javax.inject.Inject

class NoticeRepository @Inject constructor(
    private val noticeRemoteDataSource: NoticeRemoteDataSource
) {
    suspend fun response(): List<NoticesItem>? {
        val notices = noticeRemoteDataSource.requestNoticeList()?.mapToNoticeList()

        return notices?.notices
    }

    suspend fun response(id: Int): NoticesItem? {
        return noticeRemoteDataSource.requestNoticeDetail(id)?.mapToNoticesItem()
    }
}
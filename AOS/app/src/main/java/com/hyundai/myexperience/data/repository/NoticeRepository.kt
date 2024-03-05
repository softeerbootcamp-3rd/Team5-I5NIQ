package com.hyundai.myexperience.data.repository

import com.hyundai.myexperience.data.entity.notice.NoticeDetailItem
import com.hyundai.myexperience.data.entity.notice.NoticesItem
import com.hyundai.myexperience.data.mapper.notice.mapToNoticeList
import com.hyundai.myexperience.data.mapper.notice.mapToNoticesItem
import com.hyundai.myexperience.data.remote.data_source.NoticeRemoteDataSource
import javax.inject.Inject

class NoticeRepository @Inject constructor(
    private val noticeRemoteDataSource: NoticeRemoteDataSource
) {
    suspend fun response(): List<NoticesItem>? {
        val notices = noticeRemoteDataSource.requestNoticeList()?.mapToNoticeList()

        return notices?.notices
    }

    suspend fun response(id: Int): NoticeDetailItem? {
        return noticeRemoteDataSource.requestNoticeDetail(id)?.mapToNoticesItem()
    }
}
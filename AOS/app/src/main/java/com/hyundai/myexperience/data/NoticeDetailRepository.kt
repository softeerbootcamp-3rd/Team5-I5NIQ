package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.mapper.mapToNoticesItem
import com.hyundai.myexperience.data.remote.NoticeDetailRemoteDataSource
import javax.inject.Inject

class NoticeDetailRepository @Inject constructor(
    private val noticeDetailRemoteDataSource: NoticeDetailRemoteDataSource
) {
    suspend fun response(id: Int): NoticesItem? {
        return noticeDetailRemoteDataSource.responseNoticeDetail(id)?.mapToNoticesItem()
    }
}
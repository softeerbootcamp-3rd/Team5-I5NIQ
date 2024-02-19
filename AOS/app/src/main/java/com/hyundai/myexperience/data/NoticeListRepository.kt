package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.dto.NoticeListResponse
import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.mapper.mapToNoticeList
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import javax.inject.Inject

class NoticeListRepository @Inject constructor(
    private val noticeListRemoteDataSource: NoticeListRemoteDataSource
) {
    // 서버로 공지사항 목록 불러오는 요청을 보내는 함수
    suspend fun response(): List<NoticesItem>? {
        val notices = noticeListRemoteDataSource.responseNoticeList()?.mapToNoticeList()

        return notices?.notices
    }
}
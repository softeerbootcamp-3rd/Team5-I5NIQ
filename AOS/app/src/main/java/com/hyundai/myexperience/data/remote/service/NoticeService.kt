package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.notice.NoticeDetailResponse
import com.hyundai.myexperience.data.dto.notice.NoticeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticeService {
    @GET("notice/list")
    suspend fun requestNoticeList(): Response<NoticeListResponse>

    @GET("notice/{noticeId}")
    suspend fun requestNoticeDetail(@Path("noticeId") noticeId: Int): Response<NoticeDetailResponse>
}
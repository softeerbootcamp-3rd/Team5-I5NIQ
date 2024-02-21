package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.notice.NoticeDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticeDetailService {
    @GET("notice/{noticeId}")
    suspend fun responseNoticeDetail(@Path("noticeId") noticeId: Int): Response<NoticeDetailResponse>
}
package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.notice.NoticeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface NoticeListService {
    @GET("notice/list")
    suspend fun responseNoticeList(): Response<NoticeListResponse>
}
package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.notice.NoticeListResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.NoticeListService
import javax.inject.Inject

class NoticeListRemoteDataSource @Inject constructor(private val service: NoticeListService) {
    suspend fun responseNoticeList(): NoticeListResponse? {
        val response = service.responseNoticeList()

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse() // errorResponse는 수정될 수 있음
        Log.d("response", error?.message ?: "null")

        return null
    }
}
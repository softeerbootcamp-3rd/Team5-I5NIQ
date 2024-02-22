package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.notice.NoticeDetailResponse
import com.hyundai.myexperience.data.dto.notice.NoticeListResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.NoticeService
import javax.inject.Inject

class NoticeRemoteDataSource @Inject constructor(private val service: NoticeService) {
    suspend fun requestNoticeList(): NoticeListResponse? {
        val response = service.requestNoticeList()

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }

    suspend fun requestNoticeDetail(id: Int): NoticeDetailResponse? {
        val response = service.requestNoticeDetail(id)

        if (response.isSuccessful) {
            Log.d("response", "success")
            return response.body()
        }

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }
}
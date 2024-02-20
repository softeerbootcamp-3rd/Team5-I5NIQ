package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.notice.NoticeDetailResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.NoticeDetailService
import javax.inject.Inject

class NoticeDetailRemoteDataSource @Inject constructor(private val service: NoticeDetailService) {
    suspend fun responseNoticeDetail(id: Int): NoticeDetailResponse? {
        val response = service.responseNoticeDetail(id)

        if (response.isSuccessful) {
            Log.d("response", "success")
            return response.body()
        }

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }
}
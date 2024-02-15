package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.ErrorResponse
import com.hyundai.myexperience.data.remote.ServerConnection
import okhttp3.ResponseBody

fun ResponseBody.mapToErrorResponse(): ErrorResponse? {
    return ServerConnection.getInstance().responseBodyConverter<ErrorResponse>(
        ErrorResponse::class.java,
        ErrorResponse::class.java.annotations
    ).convert(this)
}
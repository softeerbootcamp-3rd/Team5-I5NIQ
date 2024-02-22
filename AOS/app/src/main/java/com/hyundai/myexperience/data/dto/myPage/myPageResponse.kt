package com.hyundai.myexperience.data.dto.myPage


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class myPageResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val level: Int,
        val name: String,
        val pastClassNum: Int,
        val recentComment: String?, // Any
        val totalClassNum: Int,
        val upcomingClass: String?  // Any
    )
}
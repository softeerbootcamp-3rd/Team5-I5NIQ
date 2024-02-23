package com.hyundai.myexperience.data.dto.my_page

import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponse(
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
        val recentComment: RecentComment?,
        val totalClassNum: Int,
        val upcomingClass: UpcomingClass?
    ) {
        @Serializable
        data class RecentComment(
            val category: String,
            val contents: String,
            val level: String,
            val programName: String
        )

        @Serializable
        data class UpcomingClass(
            val category: String,
            val level: String,
            val num: Int,
            val programName: String,
            val startDateTime: String
        )
    }
}
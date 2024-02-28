package com.hyundai.myexperience.data.entity.my_page

data class MyPage(
    val level: Int,
    val name: String,
    val pastClassNum: Int,
    val recentComment: RecentComment?,
    val totalClassNum: Int,
    val upcomingClass: UpcomingClass?
) {
    data class RecentComment(
        val category: String,
        val contents: String,
        val level: String,
        val programName: String
    )

    data class UpcomingClass(
        val category: String,
        val level: String,
        val num: Int,
        val programName: String,
        val startDateTime: String
    )
}
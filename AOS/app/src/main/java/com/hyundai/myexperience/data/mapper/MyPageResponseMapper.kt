package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.my_page.MyPageResponse
import com.hyundai.myexperience.data.entity.my_page.MyPage

fun MyPageResponse.mapToMyPage() : MyPage {
    return MyPage(
        level = result.level,
        name = result.name,
        pastClassNum = result.pastClassNum,
        recentComment = result.recentComment?.mapToRecentComment(),
        totalClassNum = result.totalClassNum,
        upcomingClass = result.upcomingClass?.mapToUpcomingClass(),
    )
}

fun MyPageResponse.Result.RecentComment.mapToRecentComment(): MyPage.RecentComment {
    return MyPage.RecentComment(
        category = this.category,
        contents = this.contents,
        level = this.level,
        programName = this.programName,
    )
}

fun MyPageResponse.Result.UpcomingClass.mapToUpcomingClass(): MyPage.UpcomingClass {
    return MyPage.UpcomingClass(
        category = this.category,
        level = this.level,
        num = this.num,
        programName = this.programName,
        startDateTime = this.startDateTime,
    )
}
package com.hyundai.myexperience.data.mapper.my_page

import com.hyundai.myexperience.data.dto.my_page.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.dto.my_page.JoinedProgramResponse
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramDetail
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramList

fun JoinedProgramResponse.mapToJoinedProgramList(): JoinedProgramList {
    return JoinedProgramList(
        joinedPrograms = result.mapToJoinedProgramList()
    )
}

fun JoinedProgramDetailResponse.mapToJoinedDetail(): JoinedProgramDetail {
    return JoinedProgramDetail(
        carName = result.carName,
        participants = result.participants,
        participationId = result.participationId,
        programCategory = result.programCategory,
        programLevel = result.programLevel,
        programName = result.programName,
        startDateTime = result.startDateTime,
    )
}

fun List<JoinedProgramResponse.Result>.mapToJoinedProgramList(): List<JoinedProgramItem> {
    val joinedProgramList = mutableListOf<JoinedProgramItem>()
    for (program in this)
        joinedProgramList.add(
            JoinedProgramItem(
                category = program.category,
                dateTime = program.dateTime,
                programName = program.programName,
                level = program.level,
                participationId = program.participationId,
                status = program.status,
            )
        )
    return joinedProgramList
}
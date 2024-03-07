package com.hyundai.myexperience.data.mapper.user

import com.hyundai.myexperience.data.dto.user.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.dto.user.JoinedProgramResponse
import com.hyundai.myexperience.data.entity.user.JoinedProgramDetail
import com.hyundai.myexperience.data.entity.user.JoinedProgramItem
import com.hyundai.myexperience.data.entity.user.JoinedProgramList

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
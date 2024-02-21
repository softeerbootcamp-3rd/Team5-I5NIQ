package com.hyundai.myexperience.data.mapper.program

import com.hyundai.myexperience.data.dto.program.ProgramCommentResponse
import com.hyundai.myexperience.data.entity.program.Comment
import com.hyundai.myexperience.data.entity.program.ProgramTrack

fun ProgramCommentResponse.Result.Comment.mapToProgramTrack(): Comment {
    return Comment(
        username = userName,
        date = createdAt ?: "",
        content = content
    )
}
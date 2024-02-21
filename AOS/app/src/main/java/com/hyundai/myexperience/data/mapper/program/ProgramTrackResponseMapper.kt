package com.hyundai.myexperience.data.mapper.program

import com.hyundai.myexperience.data.dto.program.ProgramTrackResponse
import com.hyundai.myexperience.data.entity.program.ProgramTrack

fun ProgramTrackResponse.Result.Circuit.mapToProgramTrack(): ProgramTrack {
    return ProgramTrack(
        description = description,
        image = imageUrl,
        name = name
    )
}
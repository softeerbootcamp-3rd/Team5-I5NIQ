package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.program.ProgramMajorResponse
import com.hyundai.myexperience.data.entity.ProgramMajorData

fun ProgramMajorResponse.Result.mapToProgramMajorData(): ProgramMajorData {
    return ProgramMajorData(
        estimatedDuration = estimatedDuration,
        levelName = levelName,
        maxMemberNumber = maxMemberNumber,
        programCars = programCars,
        programCategoryDescription = programCategoryDescription,
        programCategoryName = programCategoryName,
        programDescription = programDescription,
        programId = programId,
        qualification = qualification
    )
}
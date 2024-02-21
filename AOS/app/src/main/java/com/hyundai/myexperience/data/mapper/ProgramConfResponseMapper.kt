package com.hyundai.myexperience.data.mapper

import ProgramConfData
import com.hyundai.myexperience.COMPANY_GENESIS
import com.hyundai.myexperience.COMPANY_HMG
import com.hyundai.myexperience.COMPANY_HYUNDAI
import com.hyundai.myexperience.COMPANY_KIA
import com.hyundai.myexperience.PROGRAM_LEVEL_1
import com.hyundai.myexperience.PROGRAM_LEVEL_2
import com.hyundai.myexperience.PROGRAM_LEVEL_3
import com.hyundai.myexperience.PROGRAM_LEVEL_N_ADVANCED
import com.hyundai.myexperience.PROGRAM_LEVEL_N_MASTERS
import com.hyundai.myexperience.PROGRAM_OFF_ROAD
import com.hyundai.myexperience.TYPE_TAXI
import com.hyundai.myexperience.data.dto.program.ProgramConfResponse
import com.hyundai.myexperience.data.dto.program.ProgramMajorResponse
import com.hyundai.myexperience.data.entity.program.ProgramCar
import com.hyundai.myexperience.data.entity.program.ProgramMajorData

fun ProgramConfResponse.Result.mapToProgramConfData(): ProgramConfData {
    return ProgramConfData(
        id = programId,
        cars = programCars.map { it.mapToProgramCar() },
        detailDescription = detailDescription,
        images = programImages
    )
}

fun ProgramConfResponse.Result.ProgramCar.mapToProgramCar(): ProgramCar {
    return ProgramCar(
        id = carId,
        image = image,
        name = name,
        description = description,
        maxPower = maxPower,
        maxTorque = maxTorque
    )
}
package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.program.ProgramConfResponse
import com.hyundai.myexperience.data.entity.program.ProgramCar
import com.hyundai.myexperience.data.entity.program.ProgramConfData

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
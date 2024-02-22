package com.hyundai.myexperience.data.dto.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramConfResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val detailDescription: String,
        val programCars: List<ProgramCar>,
        val programId: Int,
        val programImages: List<String>
    ) {
        @Serializable
        data class ProgramCar(
            val carId: Int,
            val description: String?,
            val image: String?,
            val maxPower: String,
            val maxTorque: String,
            val name: String
        )
    }
}
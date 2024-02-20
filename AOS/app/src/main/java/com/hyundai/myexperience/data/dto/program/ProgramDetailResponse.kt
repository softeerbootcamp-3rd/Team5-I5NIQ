package com.hyundai.myexperience.data.dto.program

data class ProgramDetailResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    data class Result(
        val detailDescription: String,
        val programCars: List<ProgramCar>,
        val programId: Int,
        val programImages: List<String>
    ) {
        data class ProgramCar(
            val carId: Int,
            val description: Any,
            val image: String,
            val maxPower: String,
            val maxTorque: String,
            val name: String
        )
    }
}
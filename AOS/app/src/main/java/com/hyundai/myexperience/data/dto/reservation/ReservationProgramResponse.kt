package com.hyundai.myexperience.data.dto.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationProgramResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val programs: List<Program>
    ) {
        @Serializable
        data class Program(
            val companyCount: Int,
            val companyPrograms: List<CompanyProgram>,
            val programCategoryName: String
        ) {
            @Serializable
            data class CompanyProgram(
                val companyName: String,
                val programCount: Int,
                val programs: List<Program>
            ) {
                @Serializable
                data class Program(
                    val canReservation: Boolean,
                    val managerCompany: String,
                    val programId: Int,
                    val programLevel: String,
                    val programName: String
                )
            }
        }
    }
}
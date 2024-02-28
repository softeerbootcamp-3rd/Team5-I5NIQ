package com.hyundai.myexperience.data.dto.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramTrackResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val circuitSize: Int,
        val circuits: List<Circuit>
    ) {
        @Serializable
        data class Circuit(
            val description: String,
            val imageUrl: String,
            val name: String
        )
    }
}
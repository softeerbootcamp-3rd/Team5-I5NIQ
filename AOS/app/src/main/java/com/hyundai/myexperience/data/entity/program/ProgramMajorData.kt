package com.hyundai.myexperience.data.entity.program

data class ProgramMajorData(
    val estimatedDuration: String,
    val level: String,
    val maxMemberNumber: Int,
    val cars: List<String>,
    val programCategoryDescription: String,
    val company: String,
    val programDescription: String,
    val id: Int,
    val qualification: String
)
package com.hyundai.myexperience.data.entity

data class ProgramMajorData(
    val estimatedDuration: String,
    val levelName: String,
    val maxMemberNumber: Int,
    val programCars: List<String>,
    val programCategoryDescription: String,
    val programCategoryName: String,
    val programDescription: String,
    val programId: Int,
    val qualification: String
)
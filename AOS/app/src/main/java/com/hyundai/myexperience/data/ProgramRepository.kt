package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.ProgramMajorData
import com.hyundai.myexperience.data.mapper.mapToProgramMajorData
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import javax.inject.Inject

class ProgramRepository @Inject constructor(
    private val programRemoteDataSource: ProgramRemoteDataSource
) {
    suspend fun requestProgramMajorData(id: Int): ProgramMajorData? {
        return programRemoteDataSource.requestProgramMajorData(id)?.mapToProgramMajorData()
    }
}
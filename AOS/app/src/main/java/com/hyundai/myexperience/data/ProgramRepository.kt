package com.hyundai.myexperience.data

import ProgramConfData
import com.hyundai.myexperience.data.entity.program.ProgramMajorData
import com.hyundai.myexperience.data.mapper.mapToProgramConfData
import com.hyundai.myexperience.data.mapper.mapToProgramMajorData
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import javax.inject.Inject

class ProgramRepository @Inject constructor(
    private val programRemoteDataSource: ProgramRemoteDataSource
) {
    suspend fun requestProgramMajorData(id: Int): ProgramMajorData? {
        return programRemoteDataSource.requestProgramMajorData(id)?.mapToProgramMajorData()
    }

    suspend fun requestProgramConfData(id: Int): ProgramConfData? {
        return programRemoteDataSource.requestProgramConfData(id)?.mapToProgramConfData()
    }
}
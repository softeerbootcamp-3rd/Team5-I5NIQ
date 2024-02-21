package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.dto.program.ProgramConfResponse
import com.hyundai.myexperience.data.dto.program.ProgramMajorResponse
import com.hyundai.myexperience.data.dto.program.ProgramTrackResponse
import com.hyundai.myexperience.data.remote.service.ProgramService
import javax.inject.Inject

class ProgramRemoteDataSource @Inject constructor(private val service: ProgramService) {
    suspend fun requestProgramMajorData(id: Int): ProgramMajorResponse.Result? {
        val response = service.requestProgramMajorData(id)

        if (response.isSuccessful) return response.body()?.result

        return null
    }

    suspend fun requestProgramConfData(id: Int): ProgramConfResponse.Result? {
        val response = service.requestProgramDetailData(id)

        if (response.isSuccessful) return response.body()?.result

        return null
    }

    suspend fun requestProgramTracks(id: Int): ProgramTrackResponse.Result? {
        val response = service.requestProgramTrackData(id)

        if (response.isSuccessful) return response.body()?.result

        return null
    }
}
package com.hyundai.myexperience.data

import android.util.Log
import com.hyundai.myexperience.data.dto.my_page.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramDetail
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.data.mapper.my_page.mapToJoinedDetail
import com.hyundai.myexperience.data.mapper.my_page.mapToJoinedProgramList
import com.hyundai.myexperience.data.remote.JoinedProgramRemoteDataSource
import javax.inject.Inject

class JoinedProgramRepository @Inject constructor(
    private val joinedProgramRemoteDataSource: JoinedProgramRemoteDataSource
) {
    suspend fun requestJoinedPrograms(status: String): List<JoinedProgramItem>? {
        return joinedProgramRemoteDataSource.requestJoinedPrograms(status)?.mapToJoinedProgramList()
    }

    suspend fun requestJoinedProgramDetail(id: Long): JoinedProgramDetail? {
        Log.d("tag", "request success: ${joinedProgramRemoteDataSource.requestJoinedProgramDetail(id)?.mapToJoinedDetail()}")
        return joinedProgramRemoteDataSource.requestJoinedProgramDetail(id)?.mapToJoinedDetail()
    }
}
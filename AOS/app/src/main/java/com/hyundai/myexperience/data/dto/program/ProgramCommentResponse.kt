package com.hyundai.myexperience.data.dto.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramCommentResponse(
    val code: String,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val commentSize: Int,
        val comments: List<Comment>
    ) {
        @Serializable
        data class Comment(
            val content: String,
            val createdAt: String?,
            val userName: String
        )
    }
}
package com.sean.msainstagram.comment.dto

import com.sean.msainstagram.user.dto.AuthorDto
import java.time.Instant

data class CreateCommentResponse(
    val id: String,
    val from: AuthorDto,
    val text: String,
    val status: String = "ok",
    val createdTime: Instant,
)

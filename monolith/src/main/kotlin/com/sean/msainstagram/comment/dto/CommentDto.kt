package com.sean.msainstagram.comment.dto

import java.time.ZonedDateTime

data class CommentDto(
    val id: Long,
    val authorId: Long,
    val text: String,
    val createdTime: ZonedDateTime,
)

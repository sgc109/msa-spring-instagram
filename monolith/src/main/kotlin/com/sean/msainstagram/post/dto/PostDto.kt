package com.sean.msainstagram.post.dto

import com.sean.msainstagram.comment.dto.CommentDto
import java.time.ZonedDateTime

data class PostDto(
    val id: Long,
    val description: String,
    val slides: List<PostSlideDto>,
    val likeCount: Long = 0,
    val hasLiked: Boolean = false,
    val comments: List<CommentDto> = emptyList(),
    val commentCount: Long = 0,
    val createdAt: ZonedDateTime,
)

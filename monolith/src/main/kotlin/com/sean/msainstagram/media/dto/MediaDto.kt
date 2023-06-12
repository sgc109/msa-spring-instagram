package com.sean.msainstagram.media.dto

import com.sean.msainstagram.comment.dto.CommentDto
import java.time.ZonedDateTime

data class MediaDto(
    val id: Long,
    val caption: String?,
    val carouselMedias: List<CarouselMediaDto>,
    val likeCount: Long = 0,
    val hasLiked: Boolean = false,
    val comments: List<CommentDto> = emptyList(),
    val commentCount: Long = 0,
    val createdAt: ZonedDateTime,
)

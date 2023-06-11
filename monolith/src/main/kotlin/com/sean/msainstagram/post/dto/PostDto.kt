package com.sean.msainstagram.post.dto

import java.time.ZonedDateTime

data class PostDto(
    val id: Long,
    val description: String,
    val slides: List<PostSlideDto>,
    val createdAt: ZonedDateTime,
) {
    data class PageDto(
        val imageUrl: String,
    )
}

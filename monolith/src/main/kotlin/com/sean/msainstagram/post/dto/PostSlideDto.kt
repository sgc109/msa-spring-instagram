package com.sean.msainstagram.post.dto

data class PostSlideDto(
    val id: Long,
    val imageUrl: String? = null,
    val videoUrl: String? = null,
)

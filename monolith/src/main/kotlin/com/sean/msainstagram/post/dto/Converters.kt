package com.sean.msainstagram.post.dto

import com.sean.msainstagram.post.domain.Post
import com.sean.msainstagram.post.domain.PostSlide

object Converters {
    fun Post.toDto(): PostDto =
        PostDto(
            id = id,
            description = description,
            slides = slides.map { it.toDto() },
            createdAt = createdAt,
        )

    private fun PostSlide.toDto(): PostSlideDto =
        PostSlideDto(
            id = id,
            imageUrl = imageUrl,
            videoUrl = videoUrl,
        )
}

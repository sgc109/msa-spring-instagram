package com.sean.msainstagram.media.dto

import com.sean.msainstagram.media.entity.CarouselMedia
import com.sean.msainstagram.media.entity.Media

object Converters {
    fun Media.toDto(): MediaDto =
        MediaDto(
            id = id,
            caption = caption,
            carouselMedias = carouselMedias.map { it.toDto() },
            createdAt = createdAt,
        )

    private fun CarouselMedia.toDto(): CarouselMediaDto =
        CarouselMediaDto(
            id = id,
            imageUrl = imageUrl,
            videoUrl = videoUrl,
        )
}

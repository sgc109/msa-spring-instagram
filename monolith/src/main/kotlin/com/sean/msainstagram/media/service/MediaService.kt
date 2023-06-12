package com.sean.msainstagram.media.service

import com.sean.msainstagram.media.domain.CarouselMedia
import com.sean.msainstagram.media.domain.Media
import com.sean.msainstagram.media.dto.CarouselMediaForm
import com.sean.msainstagram.media.dto.Converters.toDto
import com.sean.msainstagram.media.dto.MediaDto
import com.sean.msainstagram.media.dto.MediaForm
import com.sean.msainstagram.media.repository.MediaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MediaService(
    private val mediaRepository: MediaRepository,
) {
    @Transactional
    fun createPost(requesterId: Long, form: MediaForm): MediaDto {
        val created = mediaRepository.save(form.toEntity(authorId = requesterId))
        return created.toDto()
    }

    private fun MediaForm.toEntity(authorId: Long): Media =
        Media(
            caption = caption,
            authorId = authorId,
        ).apply {
            this@toEntity.slides.mapIndexed { idx, form ->
                addCarouselMedia(form.toEntity(media = this, position = idx))
            }
        }

    private fun CarouselMediaForm.toEntity(media: Media, position: Int): CarouselMedia =
        CarouselMedia(
            position = position,
            imageUrl = imageUrl,
            media = media,
        )
}

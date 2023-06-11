package com.sean.msainstagram.post.service

import com.sean.msainstagram.post.domain.Post
import com.sean.msainstagram.post.domain.PostSlide
import com.sean.msainstagram.post.dto.Converters.toDto
import com.sean.msainstagram.post.dto.PostDto
import com.sean.msainstagram.post.dto.PostForm
import com.sean.msainstagram.post.dto.PostSlideForm
import com.sean.msainstagram.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    fun createPost(requesterId: Long, form: PostForm): PostDto {
        val created = postRepository.save(form.toEntity(authorId = requesterId))
        return created.toDto()
    }

    private fun PostForm.toEntity(authorId: Long): Post =
        Post(
            description = description,
            authorId = authorId,
        ).apply {
            this@toEntity.slides.mapIndexed { idx, form ->
                addSlide(form.toEntity(post = this, position = idx))
            }
        }

    private fun PostSlideForm.toEntity(post: Post, position: Int): PostSlide =
        PostSlide(
            position = position,
            imageUrl = imageUrl,
            post = post,
        )
}

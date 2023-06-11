package com.sean.msainstagram.feed.service

import com.sean.msainstagram.feed.dto.UserFeedPage
import com.sean.msainstagram.post.domain.PostRepository
import com.sean.msainstagram.post.dto.Converters.toDto
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FeedService(
    private val postRepository: PostRepository,
) {
    fun getUserFeed(authorId: Long, pageSize: Int, firstId: Long?): UserFeedPage {
        val pageable = PageRequest.ofSize(pageSize + 1)
        val (posts, nextFirstId) = (firstId?.let {
            postRepository.listNByAuthorIdFromLastId(authorId = authorId, lastId = it, pageable = pageable)
        } ?: postRepository.listNByAuthorId(authorId = authorId, pageable = pageable))
            .let {
                if (it.size < pageSize + 1) {
                    it to null
                } else {
                    it.dropLast(1) to it.last().id
                }
            }

        return UserFeedPage(
            posts = posts.map { it.toDto() },
            nextPageKey = nextFirstId,
        )
    }
}

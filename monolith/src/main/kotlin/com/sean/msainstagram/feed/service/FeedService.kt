package com.sean.msainstagram.feed.service

import com.sean.msainstagram.feed.dto.UserFeedPage
import com.sean.msainstagram.media.dto.Converters.toDto
import com.sean.msainstagram.media.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedService(
    private val mediaRepository: MediaRepository,
) {
    suspend fun getUserFeed(authorId: Long, pageSize: Int, firstId: Long?): UserFeedPage {
        val pageable = PageRequest.ofSize(pageSize + 1)
        val (posts, nextFirstId) = withContext(Dispatchers.IO) {
            (
                firstId?.let {
                    mediaRepository.listNByAuthorIdFromLastId(authorId = authorId, lastId = it, pageable = pageable)
                } ?: mediaRepository.listNByAuthorId(authorId = authorId, pageable = pageable)
                )
                .let {
                    if (it.size < pageSize + 1) {
                        it to null
                    } else {
                        it.dropLast(1) to it.last().id
                    }
                }
        }

        return UserFeedPage(
            medias = posts.map { it.toDto() },
            nextMaxId = nextFirstId,
        )
    }
}

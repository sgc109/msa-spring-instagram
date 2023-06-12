package com.sean.msainstagram.feed.api

import com.sean.msainstagram.comment.entity.CommentTargetType
import com.sean.msainstagram.comment.service.CommentService
import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.feed.dto.SavedPostsFeedPage
import com.sean.msainstagram.feed.dto.UserFeedPage
import com.sean.msainstagram.feed.service.FeedService
import com.sean.msainstagram.like.dto.LikeInfo
import com.sean.msainstagram.like.dto.LikeTargetType
import com.sean.msainstagram.like.service.LikeService
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/feed")
class FeedController(
    private val feedService: FeedService,
    private val likeService: LikeService,
    private val commentService: CommentService,
) : FeedApi {
    @GetMapping("/user/{userId}")
    override fun getUserFeed(
        @PathVariable userId: Long,
        @RequestParam count: Int?,
        @RequestParam maxId: Long?,
    ): UserFeedPage = runBlocking {
        val requesterId = DUMMY_REQUESTER_ID

        val feedPage = feedService.getUserFeed(
            authorId = userId,
            pageSize = count ?: DEFAULT_PAGE_SIZE,
            firstId = maxId,
        )

        val originalPosts = feedPage.medias
        val originalMediaIds = originalPosts.map { it.id }

        val likeInfosJob = async {
            fetchLikeInfosAsync(mediaIds = originalMediaIds, requesterId = requesterId)
        }

        val commentCountJob = async {
            fetchCommentCountAsync(mediaIds = originalMediaIds)
        }

        val postsWithLikeInfo = originalPosts.zip(likeInfosJob.await()).map { (post, likeInfo) ->
            post.copy(
                likeCount = likeInfo.likeCount,
                hasLiked = likeInfo.hasLiked,
            )
        }

        val postsWithLikeInfoAndCommentCount =
            postsWithLikeInfo.zip(commentCountJob.await()).map { (post, commentCount) ->
                post.copy(
                    commentCount = commentCount,
                )
            }

        feedPage.copy(medias = postsWithLikeInfoAndCommentCount)
    }

    @GetMapping("/saved/posts")
    override fun getSavedPostsFeed(maxId: Long?): SavedPostsFeedPage {
        // TODO: implement saved post by aggregating saveService and mediaService
        return SavedPostsFeedPage(items = emptyList(), moreAvailable = false)
    }

    private suspend fun fetchLikeInfosAsync(mediaIds: List<Long>, requesterId: Long): List<LikeInfo> {
        return likeService.batchGetLikeInfo(
            likerId = requesterId,
            targetType = LikeTargetType.POST,
            targetIds = mediaIds,
        )
    }

    private suspend fun fetchCommentCountAsync(mediaIds: List<Long>): List<Long> {
        return commentService.batchGetCommentCounts(
            targetType = CommentTargetType.POST,
            targetIds = mediaIds,
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 12
    }
}

package com.sean.msainstagram.feed.api

import com.sean.msainstagram.comment.domain.CommentTargetType
import com.sean.msainstagram.comment.service.CommentService
import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
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
            firstId = maxId
        )

        val originalPosts = feedPage.posts
        val originalPostIds = originalPosts.map { it.id }

        val likeInfosJob = async {
            fetchLikeInfosAsync(postIds = originalPostIds, requesterId = requesterId)
        }

        val commentCountJob = async {
            fetchCommentCountAsync(postIds = originalPostIds)
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

        feedPage.copy(posts = postsWithLikeInfoAndCommentCount)
    }

    private suspend fun fetchLikeInfosAsync(postIds: List<Long>, requesterId: Long): List<LikeInfo> {
        return likeService.batchGetLikeInfo(
            likerId = requesterId,
            targetType = LikeTargetType.POST,
            targetIds = postIds,
        )
    }

    private suspend fun fetchCommentCountAsync(postIds: List<Long>): List<Long> {
        return commentService.batchGetCommentCounts(
            targetType = CommentTargetType.POST,
            targetIds = postIds
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 12
    }
}

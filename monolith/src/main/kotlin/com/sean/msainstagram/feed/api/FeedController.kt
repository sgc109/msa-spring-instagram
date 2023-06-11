package com.sean.msainstagram.feed.api

import com.sean.msainstagram.feed.dto.UserFeedPage
import com.sean.msainstagram.feed.service.FeedService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/feed")
class FeedController(
    private val feedService: FeedService,
) : FeedApi {
    @GetMapping("/user/{userId}")
    override fun getUserFeed(
        @PathVariable userId: Long,
        @RequestParam count: Int?,
        @RequestParam maxId: Long?,
    ): UserFeedPage {
        return feedService.getUserFeed(
            authorId = userId,
            pageSize = count ?: DEFAULT_PAGE_SIZE,
            firstId = maxId
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 12
    }
}

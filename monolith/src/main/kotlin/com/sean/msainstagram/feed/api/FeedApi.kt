package com.sean.msainstagram.feed.api

import com.sean.msainstagram.feed.dto.SavedPostsFeedPage
import com.sean.msainstagram.feed.dto.UserFeedPage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Feed API")
interface FeedApi {
    @Operation(summary = "get feed of given user")
    fun getUserFeed(
        userId: Long,
        count: Int?,
        maxId: Long?,
    ): UserFeedPage

    @Operation(summary = "get saved posts feed in my profile")
    fun getSavedPostsFeed(maxId: Long?): SavedPostsFeedPage
}

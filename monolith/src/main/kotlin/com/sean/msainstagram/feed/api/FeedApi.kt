package com.sean.msainstagram.feed.api

import com.sean.msainstagram.feed.dto.UserFeedPage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Feed API")
interface FeedApi {
    @Operation(summary = "get feed of given user")
    fun getUserFeed(
        @Parameter userId: Long,
        @Parameter count: Int?,
        @Parameter maxId: Long?,
    ): UserFeedPage
}

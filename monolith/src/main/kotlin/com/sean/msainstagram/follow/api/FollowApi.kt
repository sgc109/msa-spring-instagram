package com.sean.msainstagram.follow.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable

@Tag(name = "Follow API")
interface FollowApi {
    @Operation(summary = "follow an user")
    fun follow(@PathVariable userId: Long)

    @Operation(summary = "unfollow an user")
    fun unfollow(@PathVariable userId: Long)
}

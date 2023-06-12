package com.sean.msainstagram.like.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Like API")
interface LikeApi {
    @Operation(summary = "like a media(post)")
    fun likeMedia(mediaId: Long)

    @Operation(summary = "unlike a media(post)")
    fun unlikeMedia(mediaId: Long)

    @Operation(summary = "like a comment")
    fun likeComment(commentId: Long)

    @Operation(summary = "unlike a comment")
    fun unlikeComment(commentId: Long)
}

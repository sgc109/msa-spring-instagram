package com.sean.msainstagram.comment.api

import com.sean.msainstagram.comment.dto.CommentForm
import com.sean.msainstagram.comment.dto.CreateCommentResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid

@Tag(name = "Comment API")
interface CommentApi {
    @Operation(summary = "get comments list on a media(post)")
    fun getComments(mediaId: Long, minId: String)

    @Operation(summary = "add a comment on a media(post)")
    fun addComment(mediaId: Long, @Valid form: CommentForm): CreateCommentResponse

    @Operation(summary = "delete a comment on a media(post)")
    fun deleteComment(mediaId: Long, commentId: Long)
}

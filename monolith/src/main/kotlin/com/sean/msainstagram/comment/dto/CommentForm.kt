package com.sean.msainstagram.comment.dto

import javax.validation.constraints.NotEmpty

data class CommentForm(
    @field:NotEmpty
    val commentText: String,
    val repliedToCommentId: Long?,
)

package com.sean.msainstagram.comment.dto

import com.sean.msainstagram.comment.entity.Comment

object Converters {
    fun Comment.toDto(): CommentDto =
        CommentDto(
            id = id,
            authorId = authorId,
            text = text,
            createdTime = createdAt,
        )
}

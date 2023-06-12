package com.sean.msainstagram.comment.repository

import com.sean.msainstagram.comment.entity.CommentCount
import com.sean.msainstagram.comment.entity.CommentTargetType
import org.springframework.data.jpa.repository.JpaRepository

interface CommentCountRepository : JpaRepository<CommentCount, Long> {
    fun findByTargetIdAndTargetType(
        targetId: Long,
        targetType: CommentTargetType
    ): CommentCount?

    fun findAllByTargetTypeAndTargetIdIn(
        targetType: CommentTargetType,
        targetIds: List<Long>,
    ): List<CommentCount>
}

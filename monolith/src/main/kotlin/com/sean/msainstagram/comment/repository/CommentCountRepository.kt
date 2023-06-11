package com.sean.msainstagram.comment.repository

import com.sean.msainstagram.comment.domain.CommentCount
import com.sean.msainstagram.comment.domain.CommentTargetType
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

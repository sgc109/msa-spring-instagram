package com.sean.msainstagram.like.repository

import com.sean.msainstagram.like.domain.Like
import com.sean.msainstagram.like.dto.LikeTargetType
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long> {
    fun deleteByTargetIdAndTargetType(targetId: Long, targetType: LikeTargetType)

    fun findAllByLikerIdAndTargetTypeAndTargetIdIn(
        likerId: Long,
        targetType: LikeTargetType,
        targetIds: List<Long>,
    ): List<Like>
}

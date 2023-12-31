package com.sean.msainstagram.like.repository

import com.sean.msainstagram.like.dto.LikeTargetType
import com.sean.msainstagram.like.entity.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long> {
    fun findAllByLikerIdAndTargetTypeAndTargetIdIn(
        likerId: Long,
        targetType: LikeTargetType,
        targetIds: List<Long>,
    ): List<Like>
}

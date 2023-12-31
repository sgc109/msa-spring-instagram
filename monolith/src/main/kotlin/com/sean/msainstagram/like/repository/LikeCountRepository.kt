package com.sean.msainstagram.like.repository

import com.sean.msainstagram.like.dto.LikeTargetType
import com.sean.msainstagram.like.entity.LikeCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LikeCountRepository : JpaRepository<LikeCount, Long> {
    @Query(
        "SELECT likeCount " +
            "FROM LikeCount likeCount " +
            "WHERE likeCount.targetId = :targetId AND likeCount.targetType = :targetType",
    )
    fun findByTargetIdAndTargetTypeOrNull(
        @Param("targetId") targetId: Long,
        @Param("targetType") targetType: LikeTargetType,
    ): LikeCount?

    fun findAllByTargetTypeAndTargetIdIn(targetType: LikeTargetType, targetIds: List<Long>): List<LikeCount>
}

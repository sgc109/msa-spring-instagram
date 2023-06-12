package com.sean.msainstagram.like.entity

import com.sean.msainstagram.like.dto.LikeTargetType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(
    indexes = [
        Index(
            name = "uk_like_count_target_id_target_type",
            columnList = "targetId,targetType",
            unique = true,
        ),
    ],
)
class LikeCount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var targetId: Long,

    @Column(nullable = false)
    var targetType: LikeTargetType,

    @Column(nullable = false)
    var count: Long = 0
) {
}

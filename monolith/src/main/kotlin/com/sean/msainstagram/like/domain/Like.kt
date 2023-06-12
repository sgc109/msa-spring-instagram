package com.sean.msainstagram.like.domain

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
    name = "user_like",
    indexes = [
        Index(
            name = "uk_user_like_target_id_target_type_liker_id",
            columnList = "targetId,targetType,likerId",
            unique = true,
        ),
    ],
)
class Like(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var likerId: Long = 0,

    @Column(nullable = false)
    var targetId: Long = 0,

    @Column(nullable = false)
    var targetType: LikeTargetType = LikeTargetType.POST
) {
}

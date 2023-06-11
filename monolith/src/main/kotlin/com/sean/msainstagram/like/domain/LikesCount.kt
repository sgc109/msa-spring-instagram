package com.sean.msainstagram.like.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class LikesCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var targetId: Long = 0

    @Column(nullable = false)
    var targetType: LikeTargetType = LikeTargetType.POST
}

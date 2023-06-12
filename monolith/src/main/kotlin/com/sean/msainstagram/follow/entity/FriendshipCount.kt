package com.sean.msainstagram.follow.entity

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
            name = "uk_user_id",
            columnList = "userId",
            unique = true,
        ),
    ],
)
class FriendshipCount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, updatable = false)
    val userId: Long,

    @Column(nullable = false)
    var followingCount: Long = 0,

    @Column(nullable = false)
    var followedCount: Long = 0,
)

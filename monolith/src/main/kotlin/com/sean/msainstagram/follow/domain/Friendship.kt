package com.sean.msainstagram.follow.domain

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
            name = "uk_friendship_follower_id_followee_id",
            columnList = "followerId,followeeId",
            unique = true,
        ),
    ],
)
class Friendship(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var followerId: Long = 0,

    @Column(nullable = false)
    var followeeId: Long = 0,

    @Column(nullable = false)
    var pending: Boolean = false,
) {
}

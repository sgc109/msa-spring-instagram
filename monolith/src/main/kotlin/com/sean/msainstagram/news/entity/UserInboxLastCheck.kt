package com.sean.msainstagram.news.entity

import java.time.Instant
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
class UserInboxLastCheck(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    val userId: Long,

    val timestamp: Instant,
) {
}

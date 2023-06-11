package com.sean.msainstagram.comment.domain

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column
    val parentId: Long? = null,

    @Column(nullable = false)
    val authorId: Long,

    @Column(nullable = false)
    val targetId: Long,

    @Column(length = 1000, nullable = false)
    var text: String,

    @Column(nullable = false, updatable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
}

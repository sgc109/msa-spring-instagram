package com.sean.msainstagram.comment.domain

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var parentId: Long? = null

    @Column(nullable = false)
    var authorId: Long = 0

    @Column(nullable = false)
    var targetId: Long = 0

    @Column(length = 1000, nullable = false)
    var text: String = ""

    @Column(nullable = false)
    var createdAt: ZonedDateTime = ZonedDateTime.now()
}

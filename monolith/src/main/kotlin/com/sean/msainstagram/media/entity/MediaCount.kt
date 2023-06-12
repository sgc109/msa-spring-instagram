package com.sean.msainstagram.media.entity

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
            name = "uk_media_count_author_id",
            columnList = "authorId",
            unique = true,
        ),
    ],
)

class MediaCount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, updatable = false)
    val authorId: Long,

    @Column(nullable = false)
    val count: Long = 0,
)

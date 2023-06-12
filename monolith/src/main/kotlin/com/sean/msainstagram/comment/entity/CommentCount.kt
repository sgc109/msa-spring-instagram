package com.sean.msainstagram.comment.entity

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
            name = "uk_comment_count_target_id_target_type",
            columnList = "targetId,targetType",
            unique = true,
        ),
    ],
)
class CommentCount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    val targetId: Long = 0,

    @Column
    val targetType: CommentTargetType,

    @Column(nullable = false)
    var count: Long = 0,
)

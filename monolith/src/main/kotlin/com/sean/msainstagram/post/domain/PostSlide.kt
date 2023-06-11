package com.sean.msainstagram.post.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class PostSlide(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "post_id", nullable = false, insertable = false, updatable = false)
    var postId: Long = 0,

    @Column(nullable = false, updatable = false)
    val position: Int,

    var imageUrl: String? = null,

    var videoUrl: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, foreignKey = ForeignKey(name = "none"))
    var post: Post,
)

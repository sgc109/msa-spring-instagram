package com.sean.msainstagram.media.domain

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
class CarouselMedia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "media_id", nullable = false, insertable = false, updatable = false)
    var mediaId: Long = 0,

    @Column(nullable = false, updatable = false)
    var position: Int,

    var imageUrl: String? = null,

    var videoUrl: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id", nullable = false, foreignKey = ForeignKey(name = "none"))
    var media: Media,
)

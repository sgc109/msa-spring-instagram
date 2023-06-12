package com.sean.msainstagram.media.entity

import java.time.ZonedDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.Table

@Entity
@Table(
    indexes = [
        Index(
            name = "idx_media_author_id_01",
            columnList = "authorId,id",
        ),
    ],
)
class Media(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, updatable = false)
    val authorId: Long,

    var caption: String?,

    @Column(nullable = false)
    var carouselMediaCount: Int = 0,

    var outlinkUrl: String? = null,

    var createdAt: ZonedDateTime = ZonedDateTime.now(),

    @OrderBy("position ASC")
    @OneToMany(mappedBy = "media", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    val carouselMedias: MutableList<CarouselMedia> = mutableListOf(),
) {
    fun addCarouselMedia(carouselMedia: CarouselMedia) {
        this.carouselMedias.add(carouselMedia)
        carouselMedia.position = carouselMediaCount
        ++carouselMediaCount
    }
}

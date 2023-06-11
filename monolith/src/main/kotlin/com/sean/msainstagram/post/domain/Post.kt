package com.sean.msainstagram.post.domain

import java.time.ZonedDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OrderBy

@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, updatable = false)
    val authorId: Long,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var slidesCount: Int = 0,

    var outlinkUrl: String? = null,

    var createdAt: ZonedDateTime = ZonedDateTime.now(),

    @OrderBy("position ASC")
    @OneToMany(mappedBy = "post", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    val slides: MutableList<PostSlide> = mutableListOf(),
) {
    fun addSlide(slide: PostSlide) {
        slides.add(slide)
        slide.post = this
    }
}

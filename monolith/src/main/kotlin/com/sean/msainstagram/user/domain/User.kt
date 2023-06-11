package com.sean.msainstagram.user.domain

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
            name = "uk_user_name",
            columnList = "name",
            unique = true,
        ),
    ],
)
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false)
    var nickname: String = ""

    @Column(nullable = false)
    var bio: String = ""

    var imageUrl: String? = null
}

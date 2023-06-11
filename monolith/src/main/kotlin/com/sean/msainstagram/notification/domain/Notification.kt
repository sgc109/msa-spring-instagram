package com.sean.msainstagram.notification.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var latestRaiserId: Long,

    var penultimateRaiserId: Long?,

    @Column(nullable = false, updatable = false)
    val receiverId: Long,
)

package com.sean.msainstagram.user.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0


}

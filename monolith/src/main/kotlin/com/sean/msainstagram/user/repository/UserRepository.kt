package com.sean.msainstagram.user.repository

import com.sean.msainstagram.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByName(name: String): User?
}

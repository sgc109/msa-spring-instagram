package com.sean.msainstagram.follow.repository

import com.sean.msainstagram.follow.domain.FriendshipCount
import org.springframework.data.jpa.repository.JpaRepository

interface FriendshipCountRepository : JpaRepository<FriendshipCount, Long> {
    fun findByUserId(userId: Long): FriendshipCount?
}

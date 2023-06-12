package com.sean.msainstagram.follow.repository

import com.sean.msainstagram.follow.entity.Friendship
import org.springframework.data.jpa.repository.JpaRepository

interface FriendshipRepository : JpaRepository<Friendship, Long> {
    fun findByFollowerIdAndFolloweeId(followerId: Long, followeeId: Long): Friendship?
}

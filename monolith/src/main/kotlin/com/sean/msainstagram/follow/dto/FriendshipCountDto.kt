package com.sean.msainstagram.follow.dto

data class FriendshipCountDto(
    val followCount: Long,
    val followedByCount: Long,
) {
    companion object {
        val DEFAULT_FRIENDSHIP_COUNT = FriendshipCountDto(followCount = 0, followedByCount = 0)
    }
}

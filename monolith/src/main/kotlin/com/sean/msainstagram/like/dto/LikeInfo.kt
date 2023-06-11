package com.sean.msainstagram.like.dto

data class LikeInfo(
    val hasLiked: Boolean,
    val likeCount: Long,
) {
    companion object {
        val DEFAULT_LIKE_INFO = LikeInfo(hasLiked = false, likeCount = 0)
    }
}

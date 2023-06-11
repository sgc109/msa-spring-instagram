package com.sean.msainstagram.feed.dto

import com.sean.msainstagram.post.dto.PostDto

data class UserFeedPage(
    val posts: List<PostDto>,
    val nextMaxId: Long?,
)

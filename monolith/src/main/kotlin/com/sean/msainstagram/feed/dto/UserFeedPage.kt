package com.sean.msainstagram.feed.dto

import com.sean.msainstagram.media.dto.MediaDto

data class UserFeedPage(
    val medias: List<MediaDto>,
    val nextMaxId: Long?,
)

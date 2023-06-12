package com.sean.msainstagram.feed.dto

import com.sean.msainstagram.media.dto.MediaDto

data class SavedPostsFeedPage(
    val items: List<MediaDto>,
    val moreAvailable: Boolean,
)

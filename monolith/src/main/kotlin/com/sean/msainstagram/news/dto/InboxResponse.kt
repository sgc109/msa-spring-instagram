package com.sean.msainstagram.news.dto

import java.time.Instant

data class InboxResponse(
    val lastChecked: Instant,
    val newStories: List<InboxStoryDto>,
    val oldStories: List<InboxStoryDto>,
)

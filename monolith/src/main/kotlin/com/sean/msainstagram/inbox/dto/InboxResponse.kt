package com.sean.msainstagram.inbox.dto

import java.time.Instant

data class InboxResponse(
    val lastChecked: Instant,
    val newStories: List<InboxStoryDto>,
    val oldStories: List<InboxStoryDto>,
)

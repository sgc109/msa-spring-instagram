package com.sean.msainstagram.inbox.repository

import com.sean.msainstagram.inbox.entity.InboxStory
import org.springframework.data.jpa.repository.JpaRepository

interface InboxStoryRepository : JpaRepository<InboxStory, Long>

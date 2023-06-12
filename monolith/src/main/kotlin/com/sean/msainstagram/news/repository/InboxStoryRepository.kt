package com.sean.msainstagram.news.repository

import com.sean.msainstagram.news.entity.InboxStory
import org.springframework.data.jpa.repository.JpaRepository

interface InboxStoryRepository : JpaRepository<InboxStory, Long> {
}

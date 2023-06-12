package com.sean.msainstagram.news.service

import com.sean.msainstagram.news.repository.InboxStoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class InboxService(
    private val inboxStoryRepository: InboxStoryRepository,
) {
    @Transactional
    fun createStory() {
    }

    fun listStories() {
    }
}

package com.sean.msainstagram.inbox.listener

import com.sean.msainstagram.inbox.service.InboxService
import com.sean.msainstagram.like.event.LikeCreated
import com.sean.msainstagram.like.event.LikeDeleted
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class LikeEventListener(
    private val inboxService: InboxService,
) {
    @TransactionalEventListener(LikeCreated::class)
    fun listenLikeCreated(event: LikeCreated) {
    }

    @TransactionalEventListener(LikeDeleted::class)
    fun listenLikeDeleted(event: LikeDeleted) {
    }
}

package com.sean.msainstagram.inbox.listener

import com.sean.msainstagram.comment.event.CommentCreated
import com.sean.msainstagram.comment.event.CommentDeleted
import com.sean.msainstagram.inbox.service.InboxService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CommentEventListener(
    private val inboxService: InboxService,
) {
    @TransactionalEventListener(CommentCreated::class)
    fun listenCommentCreated(event: CommentCreated) {
    }

    @TransactionalEventListener(CommentDeleted::class)
    fun listenCommentDeleted(event: CommentDeleted) {
    }
}

package com.sean.msainstagram.notification.listener

import com.sean.msainstagram.comment.event.CommentCreated
import com.sean.msainstagram.comment.event.CommentDeleted
import com.sean.msainstagram.notification.service.NotificationService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CommentEventListener(
    private val notificationService: NotificationService,
) {
    @TransactionalEventListener(CommentCreated::class)
    fun listenCommentCreated(event: CommentCreated) {

    }

    @TransactionalEventListener(CommentDeleted::class)
    fun listenCommentDeleted(event: CommentDeleted) {

    }
}

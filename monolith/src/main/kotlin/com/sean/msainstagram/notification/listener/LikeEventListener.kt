package com.sean.msainstagram.notification.listener

import com.sean.msainstagram.like.event.LikeCreated
import com.sean.msainstagram.like.event.LikeDeleted
import com.sean.msainstagram.notification.service.NotificationService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class LikeEventListener(
    private val notificationService: NotificationService,
) {
    @TransactionalEventListener(LikeCreated::class)
    fun listenLikeCreated(event: LikeCreated) {

    }

    @TransactionalEventListener(LikeDeleted::class)
    fun listenLikeDeleted(event: LikeDeleted) {

    }
}

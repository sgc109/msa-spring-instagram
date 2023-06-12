package com.sean.msainstagram.news.listener

import com.sean.msainstagram.comment.event.CommentCreated
import com.sean.msainstagram.comment.event.CommentDeleted
import com.sean.msainstagram.follow.event.FriendshipCreated
import com.sean.msainstagram.follow.event.FriendshipDeleted
import com.sean.msainstagram.news.service.InboxService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class FriendshipEventListener(
    private val inboxService: InboxService,
) {
    @TransactionalEventListener(CommentCreated::class)
    fun listenFriendshipCreated(event: FriendshipCreated) {
    }

    @TransactionalEventListener(CommentDeleted::class)
    fun listenFriendshipDeleted(event: FriendshipDeleted) {
    }
}

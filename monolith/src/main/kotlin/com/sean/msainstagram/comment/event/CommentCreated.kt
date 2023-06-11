package com.sean.msainstagram.comment.event

import com.sean.msainstagram.common.event.DomainEvent

class CommentCreated(
    override val aggregateId: Long,
) : DomainEvent(
    type = "CREATED",
    aggregateId = aggregateId,
    aggregateType = "Comment",
)

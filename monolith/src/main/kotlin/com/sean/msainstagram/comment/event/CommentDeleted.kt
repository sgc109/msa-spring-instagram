package com.sean.msainstagram.comment.event

import com.sean.msainstagram.common.event.DomainEvent

class CommentDeleted(
    override val aggregateId: Long,
) : DomainEvent(
    type = "DELETED",
    aggregateId = aggregateId,
    aggregateType = "Comment",
)

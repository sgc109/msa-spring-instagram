package com.sean.msainstagram.follow.event

import com.sean.msainstagram.common.event.DomainEvent

class FriendshipDeleted(
    override val aggregateId: Long,
) : DomainEvent(
    type = "DELETED",
    aggregateId = aggregateId,
    aggregateType = "Friendship",
)

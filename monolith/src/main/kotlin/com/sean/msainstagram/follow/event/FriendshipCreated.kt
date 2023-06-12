package com.sean.msainstagram.follow.event

import com.sean.msainstagram.common.event.DomainEvent

class FriendshipCreated(
    override val aggregateId: Long,
) : DomainEvent(
    type = "CREATED",
    aggregateId = aggregateId,
    aggregateType = "Friendship",
)

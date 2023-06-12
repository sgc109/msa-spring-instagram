package com.sean.msainstagram.follow.event

import com.sean.msainstagram.common.event.DomainEvent

class FriendshipUpdated(
    override val aggregateId: Long,
) : DomainEvent(
    type = "UPDATED",
    aggregateId = aggregateId,
    aggregateType = "Friendship",
)

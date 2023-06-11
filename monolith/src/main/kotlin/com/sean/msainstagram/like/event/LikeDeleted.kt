package com.sean.msainstagram.like.event

import com.sean.msainstagram.common.event.DomainEvent

class LikeDeleted(
    override val aggregateId: Long,
) : DomainEvent(
    type = "DELETED",
    aggregateId = aggregateId,
    aggregateType = "Like",
)

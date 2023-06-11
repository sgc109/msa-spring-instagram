package com.sean.msainstagram.like.event

import com.sean.msainstagram.common.event.DomainEvent

class LikeCreated(
    override val aggregateId: Long,
) : DomainEvent(
    type = "CREATED",
    aggregateId = aggregateId,
    aggregateType = "Like",
)

package com.sean.msainstagram.common.event

import java.time.Instant
import java.util.UUID

open class DomainEvent(
    open val id: String = UUID.randomUUID().toString(),
    open val type: String,
    open val aggregateId: Long,
    open val aggregateType: String,
    open val timestamp: Instant = Instant.now(),
)

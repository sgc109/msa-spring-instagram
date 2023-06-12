package com.sean.msainstagram.inbox.api

import com.sean.msainstagram.inbox.dto.InboxResponse
import com.sean.msainstagram.inbox.service.InboxService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/v1/news")
class InboxController(
    private val inboxService: InboxService,
) : InboxApi {
    @PostMapping("/inbox")
    fun inbox(): InboxResponse {
        // TODO: implement notification inbox pagination(maybe not)
        return InboxResponse(Instant.EPOCH, emptyList(), emptyList())
    }
}

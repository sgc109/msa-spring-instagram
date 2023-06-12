package com.sean.msainstagram.news.api

import com.sean.msainstagram.news.dto.InboxResponse
import com.sean.msainstagram.news.service.InboxService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/v1/news")
class NewsController(
    private val inboxService: InboxService,
) {
    @PostMapping("/inbox")
    fun inbox(): InboxResponse {
        // TODO: implement notification inbox pagination(maybe not)
        return InboxResponse(Instant.EPOCH, emptyList(), emptyList())
    }
}

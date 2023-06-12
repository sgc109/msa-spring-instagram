package com.sean.msainstagram.save.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/save")
class SaveController : SaveApi {
    @PostMapping("/{mediaId}/save")
    override fun save() {
    }

    @PostMapping("/{mediaId}/unsave")
    override fun unsave() {
    }
}

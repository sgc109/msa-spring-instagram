package com.sean.msainstagram.like.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class LikeController {
    @PostMapping("/likes/{targetId}/like")
    fun likePost() {

    }

    @PostMapping("/likes/{targetId}/unlike")
    fun unlikePost() {

    }

    @PostMapping("/comments/like/{targetId}")
    fun likeComment() {

    }

    @PostMapping("/comments/unlike/{targetId}")
    fun unlikeComment() {

    }
}

package com.sean.msainstagram.like.api

import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.like.dto.LikeTargetType
import com.sean.msainstagram.like.service.LikeService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class LikeController(
    private val likeService: LikeService,
) {
    @PostMapping("/likes/{targetId}/like")
    fun likePost(@PathVariable("targetId") targetId: Long) {
        likeService.like(
            requesterId = DUMMY_REQUESTER_ID,
            targetType = LikeTargetType.POST,
            targetId = targetId
        )
    }

    @PostMapping("/likes/{targetId}/unlike")
    fun unlikePost(@PathVariable("targetId") targetId: Long) {
        likeService.unlike(
            requesterId = DUMMY_REQUESTER_ID,
            targetType = LikeTargetType.POST,
            targetId = targetId
        )
    }

    @PostMapping("/comments/like/{targetId}")
    fun likeComment() {

    }

    @PostMapping("/comments/unlike/{targetId}")
    fun unlikeComment() {

    }
}

package com.sean.msainstagram.like.api

import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.like.dto.LikeTargetType
import com.sean.msainstagram.like.service.LikeService
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
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
    fun likePost(@PathVariable targetId: Long) {

        // TODO: check if the target exists
        try {
            likeService.like(
                requesterId = DUMMY_REQUESTER_ID,
                targetType = LikeTargetType.POST,
                targetId = targetId
            )
        } catch (ex: DataIntegrityViolationException) {
            if (ex.cause !is ConstraintViolationException) {
                throw ex
            }
        }
    }

    @PostMapping("/likes/{targetId}/unlike")
    fun unlikePost(@PathVariable targetId: Long) {
        // TODO: check if the target exists
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

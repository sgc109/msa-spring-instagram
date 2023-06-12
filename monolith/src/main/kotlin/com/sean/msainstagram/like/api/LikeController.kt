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
) : LikeApi {
    @PostMapping("/likes/{media}/like")
    override fun likeMedia(@PathVariable media: Long) {

        // TODO: check if the target exists
        try {
            likeService.like(
                requesterId = DUMMY_REQUESTER_ID,
                targetType = LikeTargetType.POST,
                media = media,
            )
        } catch (ex: DataIntegrityViolationException) {
            if (ex.cause !is ConstraintViolationException) {
                throw ex
            }
        }
    }

    @PostMapping("/likes/{mediaId}/unlike")
    override fun unlikeMedia(@PathVariable mediaId: Long) {
        // TODO: check if the target exists
        likeService.unlike(
            requesterId = DUMMY_REQUESTER_ID,
            targetType = LikeTargetType.POST,
            mediaId = mediaId,
        )
    }

    @PostMapping("/comments/like/{commentId}")
    override fun likeComment(@PathVariable commentId: Long) {
    }

    @PostMapping("/comments/unlike/{commentId}")
    override fun unlikeComment(@PathVariable commentId: Long) {
    }
}

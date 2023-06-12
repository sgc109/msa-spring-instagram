package com.sean.msainstagram.follow.api

import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.follow.service.FriendshipService
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/friendships")
class FollowController(
    private val friendshipService: FriendshipService,
) : FollowApi {
    @PostMapping("/create/{userId}")
    override fun follow(@PathVariable userId: Long) {
        val requesterId = DUMMY_REQUESTER_ID

        validateUserId(requesterId, userId)

        try {
            friendshipService.makeFriendship(
                requesterId = requesterId,
                targetUserId = userId,
            )
        } catch (ex: DataIntegrityViolationException) {
            if (ex.cause !is ConstraintViolationException) {
                throw ex
            }
        }
    }

    @PostMapping("/destroy/{userId}")
    override fun unfollow(@PathVariable userId: Long) {
        val requesterId = DUMMY_REQUESTER_ID

        validateUserId(requesterId, userId)

        friendshipService.breakFriendship(
            requesterId = requesterId,
            targetUserId = userId,
        )
    }

    private fun validateUserId(requesterId: Long, userId: Long) {
        require(userId != requesterId) {
            "You cannot follow yourself"
        }

        // TODO: check if user of userId actually exists?
    }
}

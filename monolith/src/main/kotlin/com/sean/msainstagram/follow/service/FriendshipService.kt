package com.sean.msainstagram.follow.service

import com.sean.msainstagram.follow.domain.Friendship
import com.sean.msainstagram.follow.domain.FriendshipCount
import com.sean.msainstagram.follow.dto.FriendshipCountDto
import com.sean.msainstagram.follow.dto.FriendshipCountDto.Companion.DEFAULT_FRIENDSHIP_COUNT
import com.sean.msainstagram.follow.event.FriendshipCreated
import com.sean.msainstagram.follow.event.FriendshipDeleted
import com.sean.msainstagram.follow.repository.FriendshipCountRepository
import com.sean.msainstagram.follow.repository.FriendshipRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FriendshipService(
    private val friendshipRepository: FriendshipRepository,
    private val friendshipCountRepository: FriendshipCountRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun getFriendship(userId: Long): FriendshipCountDto =
        friendshipCountRepository.findByUserId(userId)?.toDto()
            ?: DEFAULT_FRIENDSHIP_COUNT

    @Transactional
    fun makeFriendship(requesterId: Long, targetUserId: Long) {
        makeFriendshipBetween(followerId = requesterId, followeeId = targetUserId)
    }

    @Transactional
    fun breakFriendship(requesterId: Long, targetUserId: Long) {
        breakFriendshipBetween(followerId = requesterId, followeeId = targetUserId)
    }

    private fun makeFriendshipBetween(followerId: Long, followeeId: Long) {
        val created = friendshipRepository.save(Friendship(followerId = followerId, followeeId = followeeId))
        eventPublisher.publishEvent(FriendshipCreated(aggregateId = created.id))

        increaseFollowingCountBy(userId = followerId, inc = 1)
        increaseFollowedCountBy(userId = followeeId, inc = 1)
    }

    private fun breakFriendshipBetween(followerId: Long, followeeId: Long) {
        friendshipRepository.findByFollowerIdAndFolloweeId(
            followerId = followerId,
            followeeId = followeeId
        )?.also {
            friendshipRepository.deleteById(it.id)
            eventPublisher.publishEvent(FriendshipDeleted(aggregateId = it.id))

            increaseFollowingCountBy(userId = followerId, inc = -1)
            increaseFollowedCountBy(userId = followeeId, inc = -1)
        }
    }

    private fun increaseFollowingCountBy(userId: Long, inc: Long) {
        val newEntityForFollower = friendshipCountRepository.findByUserId(userId)?.apply {
            ++followingCount
        } ?: FriendshipCount(userId = userId, followingCount = inc)
        friendshipCountRepository.save(newEntityForFollower)
    }

    private fun increaseFollowedCountBy(userId: Long, inc: Long) {
        val newEntityForFollower = friendshipCountRepository.findByUserId(userId)?.apply {
            ++followedCount
        } ?: FriendshipCount(userId = userId, followedCount = inc)
        friendshipCountRepository.save(newEntityForFollower)
    }

    private fun FriendshipCount.toDto() =
        FriendshipCountDto(
            followCount = followingCount,
            followedByCount = followedCount,
        )
}

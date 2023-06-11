package com.sean.msainstagram.like.service

import com.sean.msainstagram.like.domain.Like
import com.sean.msainstagram.like.domain.LikeCount
import com.sean.msainstagram.like.dto.LikeInfo
import com.sean.msainstagram.like.dto.LikeTargetType
import com.sean.msainstagram.like.event.LikeCreated
import com.sean.msainstagram.like.event.LikeDeleted
import com.sean.msainstagram.like.repository.LikeCountRepository
import com.sean.msainstagram.like.repository.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class LikeService(
    private val likeRepository: LikeRepository,
    private val likeCountRepository: LikeCountRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    @Transactional
    fun like(requesterId: Long, targetId: Long, targetType: LikeTargetType) {
        val newEntity = Like(
            likerId = requesterId,
            targetId = targetId,
            targetType = targetType,
        )

        try {
            likeRepository.save(newEntity)
            eventPublisher.publishEvent(LikeCreated(aggregateId = newEntity.id))
        } catch (ex: Exception) {
            throw IllegalArgumentException("Cannot like same ${targetType.name} multiple times.")
        }

        val likeCount = likeCountRepository.findByTargetIdAndTargetTypeOrNull(targetId, targetType)?.apply {
            count += 1
        } ?: LikeCount(
            targetId = targetId,
            targetType = LikeTargetType.POST,
            count = 1,
        )

        likeCountRepository.save(likeCount)
    }

    @Transactional
    fun unlike(requesterId: Long, targetId: Long, targetType: LikeTargetType) {
        val entityToDel = likeRepository.findAllByLikerIdAndTargetTypeAndTargetIdIn(
            likerId = requesterId,
            targetType = targetType,
            targetIds = listOf(targetId)
        ).firstOrNull()
            ?: throw IllegalArgumentException("Cannot unlike ${targetType.name} which is not liked yet")

        likeRepository.deleteById(entityToDel.id)
        eventPublisher.publishEvent(LikeDeleted(aggregateId = entityToDel.id))

        val likeCount = likeCountRepository.findByTargetIdAndTargetTypeOrNull(targetId, targetType)?.apply {
            count -= 1
        } ?: LikeCount(
            targetId = targetId,
            targetType = LikeTargetType.POST
        )

        likeCountRepository.save(likeCount)
    }

    /**
     * @return the size of the list must be the same with param targetIds
     */
    suspend fun batchGetLikeInfo(
        likerId: Long,
        targetType: LikeTargetType,
        targetIds: List<Long>,
    ): List<LikeInfo> {
        val likedTargetIdsSet = withContext(Dispatchers.IO) {
            likeRepository.findAllByLikerIdAndTargetTypeAndTargetIdIn(likerId, targetType, targetIds)
                .map { it.targetId }
                .toSet()
        }

        val likeCountsByTargetId = withContext(Dispatchers.IO) {
            likeCountRepository.findAllByTargetTypeAndTargetIdIn(targetType, targetIds)
                .associateBy { it.targetId }
        }

        return targetIds.map { targetId ->
            likeCountsByTargetId[targetId]?.let {
                LikeInfo(
                    hasLiked = likedTargetIdsSet.contains(targetId),
                    likeCount = it.count,
                )
            } ?: LikeInfo.DEFAULT_LIKE_INFO
        }
    }
}

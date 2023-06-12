package com.sean.msainstagram.comment.service

import com.sean.msainstagram.comment.entity.Comment
import com.sean.msainstagram.comment.entity.CommentCount
import com.sean.msainstagram.comment.entity.CommentTargetType
import com.sean.msainstagram.comment.dto.CommentDto
import com.sean.msainstagram.comment.dto.CommentForm
import com.sean.msainstagram.comment.dto.Converters.toDto
import com.sean.msainstagram.comment.event.CommentCreated
import com.sean.msainstagram.comment.event.CommentDeleted
import com.sean.msainstagram.comment.repository.CommentCountRepository
import com.sean.msainstagram.comment.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentCountRepository: CommentCountRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {
    @Transactional
    fun addComment(requesterId: Long, mediaId: Long, form: CommentForm): CommentDto {
        form.repliedToCommentId?.let {
            val parentComment = commentRepository.findByIdOrNull(it)
                ?: throw IllegalArgumentException(
                    "Parent comment not found(id=$it): comment you are replying to is not found"
                )

            if (parentComment.parentId != null) {
                throw IllegalArgumentException("You cannot add comment on non-root comments.")
            }
        }

        val created = commentRepository.save(form.toEntity(authorId = requesterId, mediaId = mediaId))
        eventPublisher.publishEvent(CommentCreated(aggregateId = created.id))

        increasePostCommentCountBy(mediaId = mediaId, inc = 1)

        form.repliedToCommentId?.let {
            increaseSubCommentCountByOne(parentId = it, inc = 1)
        }

        return created.toDto()
    }

    @Transactional
    fun deleteComment(requesterId: Long, mediaId: Long, commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("Comment not found(id=$commentId).")

        // TODO: Permit if requester is the author of the media where comment is on
        if (comment.authorId != requesterId) {
            throw IllegalArgumentException("You cannot delete other's comment.")
        }

        commentRepository.deleteById(commentId)
        eventPublisher.publishEvent(CommentDeleted(aggregateId = commentId))

        increasePostCommentCountBy(mediaId = mediaId, inc = -1)

        comment.parentId?.let {
            increaseSubCommentCountByOne(parentId = it, inc = -1)
        }
    }

    private fun increasePostCommentCountBy(mediaId: Long, inc: Long) {
        val newEntity = commentCountRepository.findByTargetIdAndTargetType(
            targetId = mediaId,
            targetType = CommentTargetType.POST
        )?.apply {
            count += inc
        } ?: CommentCount(
            targetId = mediaId,
            targetType = CommentTargetType.POST,
            count = inc
        )
        commentCountRepository.save(newEntity)
    }

    private fun increaseSubCommentCountByOne(parentId: Long, inc: Long) {
        val newEntity = commentCountRepository.findByTargetIdAndTargetType(
            targetId = parentId,
            targetType = CommentTargetType.COMMENT
        )?.apply {
            count += inc
        } ?: CommentCount(
            targetId = parentId,
            targetType = CommentTargetType.COMMENT,
            count = inc
        )
        commentCountRepository.save(newEntity)
    }

    private fun CommentForm.toEntity(authorId: Long, mediaId: Long): Comment =
        Comment(
            parentId = repliedToCommentId,
            authorId = authorId,
            text = commentText,
            mediaId = mediaId,
        )

    /**
     * @return Size of return list must be the same with mediaIds param.
     */
    suspend fun batchGetCommentCounts(targetType: CommentTargetType, targetIds: List<Long>): List<Long> {
        val commentCountByTargetId = withContext(Dispatchers.IO) {
            commentCountRepository.findAllByTargetTypeAndTargetIdIn(
                targetType = targetType,
                targetIds = targetIds
            )
        }.associateBy { it.targetId }

        return targetIds.map {
            commentCountByTargetId[it]?.count ?: 0
        }
    }
}

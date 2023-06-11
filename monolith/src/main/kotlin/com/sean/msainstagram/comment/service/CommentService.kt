package com.sean.msainstagram.comment.service

import com.sean.msainstagram.comment.domain.Comment
import com.sean.msainstagram.comment.domain.CommentCount
import com.sean.msainstagram.comment.domain.CommentTargetType
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
    fun addComment(requesterId: Long, postId: Long, form: CommentForm): CommentDto {
        form.repliedToCommentId?.let {
            val parentComment = commentRepository.findByIdOrNull(it)
                ?: throw IllegalArgumentException(
                    "Parent comment not found(id=$it): comment you are replying to is not found"
                )

            if (parentComment.parentId != null) {
                throw IllegalArgumentException("You cannot add comment on non-root comments.")
            }
        }

        val created = commentRepository.save(form.toEntity(authorId = requesterId, postId = postId))
        eventPublisher.publishEvent(CommentCreated(aggregateId = created.id))

        increasePostCommentCountBy(postId = postId, inc = 1)

        form.repliedToCommentId?.let {
            increaseSubCommentCountByOne(parentId = it, inc = 1)
        }

        return created.toDto()
    }

    @Transactional
    fun deleteComment(requesterId: Long, postId: Long, commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw IllegalArgumentException("Comment not found(id=$commentId).")

        if (comment.authorId != requesterId) {
            throw IllegalArgumentException("You cannot delete other's comment.")
        }

        commentRepository.deleteById(commentId)
        eventPublisher.publishEvent(CommentDeleted(aggregateId = commentId))

        increasePostCommentCountBy(postId = postId, inc = -1)

        comment.parentId?.let {
            increaseSubCommentCountByOne(parentId = it, inc = -1)
        }
    }

    private fun increasePostCommentCountBy(postId: Long, inc: Long) {
        commentCountRepository.findByTargetIdAndTargetType(
            targetId = postId,
            targetType = CommentTargetType.POST
        )?.apply {
            count += inc
        } ?: CommentCount(
            targetId = postId,
            targetType = CommentTargetType.POST,
            count = inc
        )
    }

    private fun increaseSubCommentCountByOne(parentId: Long, inc: Long) {
        commentCountRepository.findByTargetIdAndTargetType(
            targetId = parentId,
            targetType = CommentTargetType.COMMENT
        )?.apply {
            count += inc
        } ?: CommentCount(
            targetId = parentId,
            targetType = CommentTargetType.COMMENT,
            count = inc
        )
    }

    private fun CommentForm.toEntity(authorId: Long, postId: Long): Comment =
        Comment(
            parentId = repliedToCommentId,
            authorId = authorId,
            text = commentText,
            targetId = postId,
        )

    /**
     * @return Size of return list must be the same with postIds param.
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

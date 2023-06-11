package com.sean.msainstagram.comment.api

import com.sean.msainstagram.comment.dto.CommentDto
import com.sean.msainstagram.comment.dto.CommentForm
import com.sean.msainstagram.comment.dto.CreateCommentResponse
import com.sean.msainstagram.comment.dto.DeleteCommentResponse
import com.sean.msainstagram.comment.service.CommentService
import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.user.dto.AuthorDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/comments")
class CommentController(
    private val commentService: CommentService,
) {
    @PostMapping("/{postId}/add")
    fun addComment(
        @PathVariable("postId") postId: Long,
        @RequestBody @Valid form: CommentForm
    ): CreateCommentResponse {
        return commentService.addComment(requesterId = DUMMY_REQUESTER_ID, postId = postId, form = form)
            .toResponse()
    }

    @PostMapping("/{postId}/delete/{commentId}")
    fun deleteComment(
        @PathVariable("postId") postId: Long,
        @PathVariable("commentId") commentId: Long
    ): DeleteCommentResponse {
        commentService.deleteComment(
            requesterId = DUMMY_REQUESTER_ID,
            postId = postId,
            commentId = commentId
        )

        return DeleteCommentResponse()
    }

    private fun CommentDto.toResponse() =
        CreateCommentResponse(
            id = id.toString(),
            from = AuthorDto(
                id = authorId.toString(),
                username = "",
                fullName = "",
                profilePicture = null,
            ),
            text = text,
            createdTime = createdTime.toInstant(),
        )
}

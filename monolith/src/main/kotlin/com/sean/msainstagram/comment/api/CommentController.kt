package com.sean.msainstagram.comment.api

import com.sean.msainstagram.comment.dto.CommentDto
import com.sean.msainstagram.comment.dto.CommentForm
import com.sean.msainstagram.comment.dto.CreateCommentResponse
import com.sean.msainstagram.comment.service.CommentService
import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.user.dto.AuthorDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class CommentController(
    private val commentService: CommentService,
) {
    @GetMapping("/media/{mediaId}/comments")
    fun getComments(@PathVariable mediaId: Long, @RequestParam minId: String) {
        // TODO: comments pagination
    }

    @PostMapping("/comments/{mediaId}/add")
    fun addComment(
        @PathVariable mediaId: Long,
        @RequestBody @Valid form: CommentForm
    ): CreateCommentResponse {
        return commentService.addComment(requesterId = DUMMY_REQUESTER_ID, mediaId = mediaId, form = form)
            .toResponse()
    }

    @PostMapping("/comments/{mediaId}/delete/{commentId}")
    fun deleteComment(
        @PathVariable mediaId: Long,
        @PathVariable commentId: Long,
    ) {
        commentService.deleteComment(
            requesterId = DUMMY_REQUESTER_ID,
            mediaId = mediaId,
            commentId = commentId
        )
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

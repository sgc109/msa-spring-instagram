package com.sean.msainstagram.post.api

import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.post.dto.PostDto
import com.sean.msainstagram.post.dto.PostForm
import com.sean.msainstagram.post.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime
import java.util.Collections.emptyList
import javax.validation.Valid

@RestController
@RequestMapping("/v1/posts")
class PostController(
    private val postService: PostService,
) {
    @GetMapping("{id}")
    fun getPost(@PathVariable id: Long): PostDto {
        return PostDto(id = id, description = "", slides = emptyList(), createdAt = ZonedDateTime.now())
    }

    @PostMapping
    fun createPost(@RequestBody @Valid form: PostForm): PostDto {
        return postService.createPost(requesterId = DUMMY_REQUESTER_ID, form)
    }
}

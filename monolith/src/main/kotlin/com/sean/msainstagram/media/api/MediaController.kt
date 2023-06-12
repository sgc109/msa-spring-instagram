package com.sean.msainstagram.media.api

import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.media.dto.MediaDto
import com.sean.msainstagram.media.dto.MediaForm
import com.sean.msainstagram.media.service.MediaService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/media")
class MediaController(
    private val mediaService: MediaService,
) : MediaApi {
    @PostMapping
    override fun createMedia(@RequestBody @Valid form: MediaForm): MediaDto {
        return mediaService.createPost(requesterId = DUMMY_REQUESTER_ID, form)
    }
}

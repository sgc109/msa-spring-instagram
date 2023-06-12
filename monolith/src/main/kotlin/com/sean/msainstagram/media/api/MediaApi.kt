package com.sean.msainstagram.media.api

import com.sean.msainstagram.media.dto.MediaDto
import com.sean.msainstagram.media.dto.MediaForm
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Media API", description = "Media are posts where images and/or videos in it")
interface MediaApi {
    @Operation(summary = "upload media(post)")
    fun createMedia(form: MediaForm): MediaDto
}

package com.sean.msainstagram.post.dto

import javax.validation.Valid
import javax.validation.constraints.Size

data class PostForm(
    val description: String,
    @field:Valid
    @field:Size(min = 1, max = 10)
    val slides: List<PostSlideForm>,
)

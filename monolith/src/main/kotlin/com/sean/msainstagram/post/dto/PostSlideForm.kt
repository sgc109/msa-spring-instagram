package com.sean.msainstagram.post.dto

import javax.validation.constraints.NotEmpty

data class PostSlideForm(
    @field:NotEmpty
    val imageUrl: String,
)

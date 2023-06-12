package com.sean.msainstagram.media.dto

import javax.validation.constraints.NotEmpty

data class CarouselMediaForm(
    @field:NotEmpty
    val imageUrl: String,
)

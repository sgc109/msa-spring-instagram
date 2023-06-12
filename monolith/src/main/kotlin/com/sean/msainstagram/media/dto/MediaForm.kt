package com.sean.msainstagram.media.dto

import javax.validation.Valid
import javax.validation.constraints.Size

data class MediaForm(
    val caption: String?,
    @field:Valid
    @field:Size(min = 1, max = 10)
    val slides: List<CarouselMediaForm>,
)

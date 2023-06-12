package com.sean.msainstagram.save.dto

import javax.validation.constraints.NotEmpty

data class CollectionForm(
    @field:NotEmpty
    val name: String,
    val addedMediaIds: List<String>,
)

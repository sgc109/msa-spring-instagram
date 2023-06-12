package com.sean.msainstagram.save.dto

data class UpdateCollectionForm(
    val addedMediaIds: List<String>,
    val removedMediaIds: List<String>,
    val name: String,
)

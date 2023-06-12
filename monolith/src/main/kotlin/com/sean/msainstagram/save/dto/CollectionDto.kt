package com.sean.msainstagram.save.dto

data class CollectionDto(
    val collectionId: String,
    val collectionName: String,
    val collectionType: String = "MEDIA",
    // val coverMedia: MediaDto,
) {
    // data class MediaDto(
    //     val id: String,
    // )
}

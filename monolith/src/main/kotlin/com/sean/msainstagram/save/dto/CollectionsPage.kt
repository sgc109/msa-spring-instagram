package com.sean.msainstagram.save.dto

data class CollectionsPage(
    val items: List<CollectionDto>,
    val moreAvailable: Boolean,
    val nextMaxId: Long? = null,
)

package com.sean.msainstagram.save.api

import com.sean.msainstagram.save.dto.CollectionForm
import com.sean.msainstagram.save.dto.CollectionsPage
import com.sean.msainstagram.save.dto.UpdateCollectionForm
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Collection API")
interface CollectionApi {
    @Operation(summary = "get collections list")
    fun getCollectionsList(maxId: Long?): CollectionsPage

    @Operation(summary = "create new collection")
    fun createCollection(form: CollectionForm)

    @Operation(summary = "edit collection")
    fun editCollection(collectionId: Long, form: UpdateCollectionForm)

    @Operation(summary = "delete collection")
    fun deleteCollection(collectionId: Long)
}

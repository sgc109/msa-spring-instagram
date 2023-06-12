package com.sean.msainstagram.save.api

import com.sean.msainstagram.save.dto.CollectionForm
import com.sean.msainstagram.save.dto.UpdateCollectionForm
import com.sean.msainstagram.save.service.CollectionService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/collections")
class CollectionController(
    private val collectionService: CollectionService,
) {
    @PostMapping("/create")
    fun createCollection(@RequestBody form: CollectionForm) {

    }

    @PostMapping("/{collectionId}/edit")
    fun editCollection(
        @PathVariable collectionId: Long,
        @RequestBody form: UpdateCollectionForm
    ) {

    }

    @PostMapping("/{collectionId}/delete")
    fun deleteCollection(@PathVariable collectionId: Long) {
        collectionService.delete(collectionId)
    }
}

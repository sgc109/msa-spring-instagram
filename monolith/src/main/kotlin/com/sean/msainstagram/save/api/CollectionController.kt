package com.sean.msainstagram.save.api

import com.sean.msainstagram.common.DUMMY_REQUESTER_ID
import com.sean.msainstagram.save.dto.CollectionForm
import com.sean.msainstagram.save.dto.CollectionsPage
import com.sean.msainstagram.save.dto.UpdateCollectionForm
import com.sean.msainstagram.save.service.CollectionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/collections")
class CollectionController(
    private val collectionService: CollectionService,
) : CollectionApi {
    @GetMapping("/list")
    override fun getCollectionsList(maxId: Long?): CollectionsPage {
        val requesterId = DUMMY_REQUESTER_ID
        return collectionService.list(requesterId = requesterId, firstId = maxId)
    }

    @PostMapping("/create")
    override fun createCollection(@RequestBody form: CollectionForm) {
    }

    @PostMapping("/{collectionId}/edit")
    override fun editCollection(
        @PathVariable collectionId: Long,
        @RequestBody form: UpdateCollectionForm,
    ) {
    }

    @PostMapping("/{collectionId}/delete")
    override fun deleteCollection(@PathVariable collectionId: Long) {
        collectionService.delete(collectionId)
    }
}

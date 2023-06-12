package com.sean.msainstagram.save.service

import com.sean.msainstagram.save.dto.CollectionDto
import com.sean.msainstagram.save.dto.CollectionsPage
import com.sean.msainstagram.save.repository.CollectionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CollectionService(
    private val collectionRepository: CollectionRepository,
) {
    fun list(requesterId: Long, firstId: Long?): CollectionsPage {
        val pageSize = DEFAULT_COLLECTIONS_PAGE_SIZE
        val pageable = PageRequest.ofSize(pageSize + 1)
        val (collections, nextFirstId) =
            (
                firstId?.let {
                    collectionRepository.listNByUserIdFromLastId(
                        userId = requesterId,
                        lastId = it,
                        pageable = pageable,
                    )
                } ?: collectionRepository.listNByUserId(userId = requesterId, pageable = pageable)
                )
                .let {
                    if (it.size < pageSize + 1) {
                        it to null
                    } else {
                        it.dropLast(1) to it.last().id
                    }
                }

        return CollectionsPage(
            items = collections.map { it.toDto() },
            moreAvailable = nextFirstId != null,
            nextMaxId = nextFirstId,
        )
    }

    private fun com.sean.msainstagram.save.entity.Collection.toDto() =
        CollectionDto(
            collectionId = id.toString(),
            collectionName = name,
        )

    @Transactional
    fun delete(collectionId: Long) {
        collectionRepository.findByIdOrNull(collectionId)?.let {
            collectionRepository.delete(it)
        } ?: throw IllegalArgumentException("Collection does not exist")
    }

    companion object {
        const val DEFAULT_COLLECTIONS_PAGE_SIZE = 20
    }
}

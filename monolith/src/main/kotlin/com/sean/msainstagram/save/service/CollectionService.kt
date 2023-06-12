package com.sean.msainstagram.save.service

import com.sean.msainstagram.save.repository.CollectionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CollectionService(
    private val collectionRepository: CollectionRepository,
) {
    @Transactional
    fun delete(collectionId: Long) {
        val collection = collectionRepository.findByIdOrNull(collectionId)?.let {
            collectionRepository.delete(it)
        } ?: throw IllegalArgumentException("Collection does not exist")
    }
}

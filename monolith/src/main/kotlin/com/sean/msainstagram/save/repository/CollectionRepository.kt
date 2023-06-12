package com.sean.msainstagram.save.repository

import com.sean.msainstagram.save.entity.Collection
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CollectionRepository : JpaRepository<Collection, Long> {
    @Query(
        "SELECT collection " +
            "FROM Collection collection " +
            "WHERE collection.userId = :userId AND collection.id > :lastId " +
            "ORDER BY collection.id DESC",
    )
    fun listNByUserIdFromLastId(
        @Param("userId") userId: Long,
        @Param("lastId") lastId: Long,
        pageable: PageRequest,
    ): List<Collection>

    @Query(
        "SELECT collection " +
            "FROM Collection collection " +
            "WHERE collection.userId = :userId " +
            "ORDER BY collection.id DESC",
    )
    fun listNByUserId(
        @Param("userId") userId: Long,
        pageable: PageRequest,
    ): List<Collection>
}

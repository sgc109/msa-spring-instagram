package com.sean.msainstagram.media.repository

import com.sean.msainstagram.media.domain.Media
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MediaRepository : JpaRepository<Media, Long> {
    @Query(
        "SELECT media " +
                "FROM Media media " +
                "WHERE media.authorId = :authorId AND media.id > :lastId " +
                "ORDER BY media.id DESC"
    )
    fun listNByAuthorIdFromLastId(
        @Param("authorId") authorId: Long,
        @Param("lastId") lastId: Long,
        pageable: PageRequest,
    ): List<Media>

    @Query(
        "SELECT media " +
                "FROM Media media " +
                "WHERE media.authorId = :authorId " +
                "ORDER BY media.id DESC"
    )
    fun listNByAuthorId(
        @Param("authorId") authorId: Long,
        pageable: PageRequest,
    ): List<Media>
}

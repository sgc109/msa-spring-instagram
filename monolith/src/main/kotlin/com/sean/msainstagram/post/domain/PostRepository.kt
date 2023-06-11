package com.sean.msainstagram.post.domain

import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PostRepository : JpaRepository<Post, Long> {
    @Query(
        "SELECT post " +
                "FROM Post post " +
                "WHERE post.authorId = :authorId AND post.id > :lastId " +
                "ORDER BY post.id DESC"
    )
    fun listNByAuthorIdFromLastId(
        @Param("authorId") authorId: Long,
        @Param("lastId") lastId: Long,
        pageable: PageRequest,
    ): List<Post>

    @Query(
        "SELECT post " +
                "FROM Post post " +
                "WHERE post.authorId = :authorId " +
                "ORDER BY post.id DESC"
    )
    fun listNByAuthorId(
        @Param("authorId") authorId: Long,
        pageable: PageRequest,
    ): List<Post>
}

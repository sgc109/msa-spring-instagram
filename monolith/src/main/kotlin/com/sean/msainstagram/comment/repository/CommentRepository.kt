package com.sean.msainstagram.comment.repository

import com.sean.msainstagram.comment.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}

package com.sean.msainstagram.comment.repository

import com.sean.msainstagram.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>

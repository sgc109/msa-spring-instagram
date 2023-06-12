package com.sean.msainstagram.save.repository

import com.sean.msainstagram.save.domain.Collection
import org.springframework.data.jpa.repository.JpaRepository

interface CollectionRepository : JpaRepository<Collection, Long> {
}

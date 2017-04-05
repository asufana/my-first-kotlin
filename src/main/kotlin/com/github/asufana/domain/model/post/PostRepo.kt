package com.github.asufana.domain.model.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepo: JpaRepository<Post, Long>

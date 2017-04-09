package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.base.repo.AbstractRepo
import com.github.asufana.domain.model.post.Post
import org.springframework.stereotype.Repository

@Repository
interface PostRepoBase : AbstractRepo<Post>

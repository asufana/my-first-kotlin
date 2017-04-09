package com.github.asufana.domain.model.comment.repo

import com.github.asufana.domain.base.repo.AbstractRepo
import com.github.asufana.domain.model.comment.Comment
import org.springframework.stereotype.Repository

@Repository
interface CommentRepoBase : AbstractRepo<Comment> {
}

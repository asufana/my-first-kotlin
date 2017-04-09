package com.github.asufana.domain.model.comment.repo

import com.github.asufana.domain.model.comment.Comment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommentRepo {

    @Autowired
    private lateinit var repo: CommentRepoBase

    fun save(comment: Comment): Comment {
        return repo.saveAndFlush(comment)
    }

}

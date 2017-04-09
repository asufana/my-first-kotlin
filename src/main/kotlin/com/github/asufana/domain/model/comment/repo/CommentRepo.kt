package com.github.asufana.domain.model.comment.repo

import com.github.asufana.domain.base.repo.AbstractRepo
import com.github.asufana.domain.model.comment.Comment
import com.github.asufana.domain.model.comment.collection.CommentCollection
import com.github.asufana.domain.model.comment.collection.toCollection
import com.github.asufana.domain.model.post.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
private interface CommentRepoBase : JpaRepository<Comment, Long>

@Component
class CommentRepo : AbstractRepo() {

    @Autowired
    private lateinit var repo: CommentRepoBase

    fun findBy(post: Post): CommentCollection {
        val list = em
                .createQuery("from Comment where post = :post", Comment::class.java)
                .setParameter("post", post)
                .resultList
        return list.toCollection()
    }

    fun save(comment: Comment): Comment {
        return repo.saveAndFlush(comment)
    }

}

package com.github.asufana.domain.model.comment.repo

import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.comment.Comment
import com.github.asufana.domain.model.comment.collection.CommentCollection
import com.github.asufana.domain.model.comment.collection.toCollection
import com.github.asufana.domain.model.post.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
private interface CommentRepoBase : JpaRepository<Comment, Long>

class CommentRepo {

    companion object {
        private fun repo(): CommentRepoBase = resolve(CommentRepoBase::class.java)
        private fun em(): EntityManager = resolve(EntityManager::class.java)

        fun findBy(post: Post): CommentCollection {
            val list = em()
                    .createQuery("from Comment where post = :post", Comment::class.java)
                    .setParameter("post", post)
                    .resultList
            return list.toCollection()
        }

        fun save(comment: Comment): Comment {
            return repo().saveAndFlush(comment)
        }
    }

}

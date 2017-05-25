package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.collection.PostCollection
import com.github.asufana.domain.model.post.collection.toCollection
import com.github.asufana.domain.model.post.vo.PostId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
private interface PostRepoBase : JpaRepository<Post, Long>

class PostRepo {

    companion object {
        private fun repo(): PostRepoBase = resolve(PostRepoBase::class.java)

        fun count(): Long {
            return repo().count()
        }

        fun findAll(): PostCollection {
            val list = repo().findAll()
            return list.toCollection()
        }

        fun findOne(id: PostId): Post {
            return repo().findOne(id.value)
        }

        fun save(post: Post): Post {
            return repo().saveAndFlush(post)
        }
    }
}

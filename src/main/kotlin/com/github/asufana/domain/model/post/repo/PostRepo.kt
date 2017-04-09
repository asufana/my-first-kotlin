package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.base.repo.AbstractRepo
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.collection.PostCollection
import com.github.asufana.domain.model.post.collection.toCollection
import com.github.asufana.domain.model.post.vo.PostId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
private interface PostRepoBase : JpaRepository<Post, Long>

@Component
class PostRepo : AbstractRepo() {

    @Autowired
    private lateinit var repo: PostRepoBase

    fun findAll(): PostCollection {
        val list = repo.findAll()
        return list.toCollection()
    }

    fun findOne(id: PostId): Post {
        return repo.findOne(id.value)
    }

    fun count(): Long {
        return repo.count()
    }

    fun save(post: Post): Post {
        return repo.saveAndFlush(post)
    }
}

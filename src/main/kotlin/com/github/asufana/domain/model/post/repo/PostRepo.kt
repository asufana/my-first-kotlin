package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.collection.PostCollection
import com.github.asufana.domain.model.post.collection.toCollection
import com.github.asufana.domain.model.post.vo.PostId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostRepo {

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

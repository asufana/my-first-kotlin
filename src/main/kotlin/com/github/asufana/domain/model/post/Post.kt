package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.comment.collection.CommentCollection
import com.github.asufana.domain.model.comment.repo.CommentRepo
import com.github.asufana.domain.model.post.repo.PostRepo
import com.github.asufana.domain.model.post.vo.PostId
import com.github.asufana.domain.model.post.vo.PostName
import org.hibernate.internal.util.StringHelper.*
import javax.persistence.Entity
import javax.persistence.Table

/** 投稿 */
@Entity
@Table(name="posts")
class Post private constructor() : AbstractEntity() {

    lateinit var name: PostName

    constructor(name: PostName): this() {
        this.name = name

        isSatisfied()
    }

    override fun isSatisfied() {
        assert(isNotEmpty(name.value))
    }

    fun id(): PostId {
        return PostId(this.id)
    }

    fun comments(): CommentCollection {
        val commentRepo = resolve(CommentRepo::class.java)
        return commentRepo.findBy(this)
    }

    fun save(): Post {
        isSatisfied()
        return repo().save(this)
    }

    private fun repo(): PostRepo {
        return resolve(PostRepo::class.java)
    }
}

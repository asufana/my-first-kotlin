package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.repo.PostRepoBase
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

    fun save(): Post {
        isSatisfied()
        return repo().saveAndFlush(this)
    }

    private fun repo(): PostRepoBase {
        return resolve(PostRepoBase::class.java)
    }
}

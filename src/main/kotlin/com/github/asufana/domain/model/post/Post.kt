package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.AbstractEntity
import com.github.asufana.domain.base.resolve
import javax.persistence.Entity
import javax.persistence.Table

/** 投稿 */
@Entity
@Table(name="posts")
class Post private constructor() : AbstractEntity() {

    lateinit var name: String

    constructor(name: String): this() {
        this.name = name

        isSatisfied()
    }

    override fun isSatisfied() {}

    fun save(): Post {
        return repo().saveAndFlush(this)
    }

    private fun repo(): PostRepo {
        return resolve(PostRepo::class.java)
    }
}

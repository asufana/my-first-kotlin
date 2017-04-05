package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.BaseEntity
import com.github.asufana.domain.base.UNSAVED_ID
import com.github.asufana.domain.base.resolve
import javax.persistence.Entity
import javax.persistence.Table

/** 投稿 */
@Entity
@Table(name="posts")
class Post private constructor() : BaseEntity() {

    lateinit var name: String

    constructor(name: String): this() {
        this.name = name
    }

    fun save(): Post {
        return repo().saveAndFlush(this)
    }

    fun isSaved(): Boolean {
        return id != UNSAVED_ID
    }

    private fun repo(): PostRepo {
        return resolve(PostRepo::class.java)
    }

}

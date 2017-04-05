package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.resolve
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/** 未保存Entity時のID値 */
const val UNSAVED_ID: Long = -1L

/** 投稿 */
@Entity
@Table(name="posts")
class Post private constructor(){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = UNSAVED_ID

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

package com.github.asufana.domain.model.post

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/** 投稿 */
@Entity
@Table(name="posts")
class Post private constructor(){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1

    lateinit var name: String

    constructor(name: String): this() {
        this.name = name
    }

}

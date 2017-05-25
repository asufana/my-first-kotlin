package com.github.asufana.domain.model.comment

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.model.comment.repo.CommentRepo
import com.github.asufana.domain.model.comment.vo.CommentId
import com.github.asufana.domain.model.comment.vo.CommentName
import com.github.asufana.domain.model.post.Post
import org.hibernate.internal.util.StringHelper.*
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/** 投稿コメント */
@Entity
@Table(name = "posts")
class Comment private constructor() : AbstractEntity() {

    @ManyToOne
    @JoinColumn(name = "post_id")
    lateinit var post: Post

    lateinit var name: CommentName

    constructor(post: Post, name: CommentName) : this() {
        this.post = post
        this.name = name

        isSatisfied()
    }

    override fun isSatisfied() {
        assert(isNotEmpty(name.value))
    }

    fun id(): CommentId {
        return CommentId(this.id)
    }

    fun save(): Comment {
        isSatisfied()
        return CommentRepo.save(this)
    }
}

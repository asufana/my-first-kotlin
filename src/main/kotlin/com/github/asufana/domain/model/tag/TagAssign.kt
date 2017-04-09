package com.github.asufana.domain.model.tag

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.repo.TagAssignRepo
import com.github.asufana.domain.model.tag.vo.TagId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/** タグと投稿の関連付け */
@Entity
@Table(name="tag_assign")
class TagAssign private constructor() : AbstractEntity() {

    @ManyToOne
    @JoinColumn(name = "post_id")
    lateinit var post: Post

    @ManyToOne
    @JoinColumn(name = "tag_id")
    lateinit var tag: Tag

    constructor(post: Post, tag: Tag) : this() {
        this.post = post
        this.tag = tag

        isSatisfied()
    }

    override fun isSatisfied() { }

    fun id(): TagId {
        return TagId(this.id)
    }

    fun save(): TagAssign {
        isSatisfied()
        return repo().save(this)
    }

    fun delete() {
        repo().delete(this)
    }

    private fun repo(): TagAssignRepo {
        return resolve(TagAssignRepo::class.java)
    }
}

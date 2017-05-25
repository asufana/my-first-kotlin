package com.github.asufana.domain.model.tag

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.exception.EntityException
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.repo.TagAssignRepo
import com.github.asufana.domain.model.tag.vo.TagId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

/** タグと投稿の関連付け */
@Entity
@Table(name = "tag_assign",
        uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("post_id", "tag_id"))))
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

    override fun isSatisfied() {
        //ユニークチェック
        if (!isSaved()) {
            val exists = TagAssignRepo.findBy(this.post, this.tag)
            if (exists != null) {
                throw EntityException.uniqueConstraints()
            }
        }
    }

    fun id(): TagId {
        return TagId(this.id)
    }

    fun save(): TagAssign {
        isSatisfied()
        return TagAssignRepo.save(this)
    }

    fun delete() {
        TagAssignRepo.delete(this)
    }
}

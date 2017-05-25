package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.model.comment.collection.CommentCollection
import com.github.asufana.domain.model.comment.repo.CommentRepo
import com.github.asufana.domain.model.post.repo.PostRepo
import com.github.asufana.domain.model.post.vo.PostId
import com.github.asufana.domain.model.post.vo.PostName
import com.github.asufana.domain.model.tag.Tag
import com.github.asufana.domain.model.tag.collection.TagCollection
import com.github.asufana.domain.model.tag.repo.TagAssignRepo
import org.hibernate.internal.util.StringHelper.*
import javax.persistence.Entity
import javax.persistence.Table

/** 投稿 */
@Entity
@Table(name = "posts")
class Post private constructor() : AbstractEntity() {

    lateinit var name: PostName

    constructor(name: PostName) : this() {
        this.name = name

        isSatisfied()
    }

    override fun isSatisfied() {
        assert(isNotEmpty(name.value))
    }

    fun id(): PostId {
        return PostId(this.id)
    }

    /** タグ付け登録 */
    fun assign(tag: Tag): Post {
        TagAssignRepo.assign(this, tag)
        return this
    }

    /** タグ付け解除 */
    fun unAssign(tag: Tag): Post {
        TagAssignRepo.unAssign(this, tag)
        return this
    }

    /** 関連付けられたコメント一覧 */
    fun comments(): CommentCollection {
        return CommentRepo.findBy(this)
    }

    /** 関連付けられたタグ一覧 */
    fun tags(): TagCollection {
        return TagAssignRepo.findBy(this)
    }

    fun save(): Post {
        isSatisfied()
        return PostRepo.save(this)
    }
}

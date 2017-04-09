package com.github.asufana.domain.model.tag

import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.collection.PostCollection
import com.github.asufana.domain.model.tag.repo.TagAssignRepo
import com.github.asufana.domain.model.tag.repo.TagRepo
import com.github.asufana.domain.model.tag.vo.TagId
import com.github.asufana.domain.model.tag.vo.TagName
import org.hibernate.annotations.common.util.StringHelper.*
import javax.persistence.Entity
import javax.persistence.Table

/** タグ */
@Entity
@Table(name = "tags")
class Tag private constructor() : AbstractEntity() {

    lateinit var name: TagName

    constructor(name: TagName) : this() {
        this.name = name

        isSatisfied()
    }

    override fun isSatisfied() {
        assert(isNotEmpty(name.value))
    }

    fun id(): TagId {
        return TagId(this.id)
    }

    /** 関連付けられた投稿一覧 */
    fun posts(): PostCollection {
        val tagAssignRepo = resolve(TagAssignRepo::class.java)
        return tagAssignRepo.findBy(this)
    }

    fun save(): Tag {
        isSatisfied()
        return repo().save(this)
    }

    private fun repo(): TagRepo {
        return resolve(TagRepo::class.java)
    }
}

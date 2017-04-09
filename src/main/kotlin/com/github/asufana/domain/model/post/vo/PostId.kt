package com.github.asufana.domain.model.post.vo

import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.repo.PostRepoBase

data class PostId(val value: Long) {

    /** エンティティ取得 */
    fun toEntity(): Post {
        return resolve(PostRepoBase::class.java).findOne(value)
    }
}

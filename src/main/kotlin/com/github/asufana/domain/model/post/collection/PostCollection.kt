package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.collection.TagCollection
import com.github.asufana.domain.model.tag.collection.toCollection

/** Collection変換 */
fun List<Post>.toCollection(): PostCollection {
    return PostCollection(this)
}

class PostCollection(list: List<Post>) : AbstractCollection<PostCollection, Post>(list) {

    /** コメントある投稿のみ抽出 */
    fun hasComments(): PostCollection {
        return filter { !it.comments().isEmpty() }
    }

    /** タグ一覧に変換 */
    fun toTags(): TagCollection {
        val tags = list.flatMap { it.tags().list }
        return tags.toCollection()
    }
}


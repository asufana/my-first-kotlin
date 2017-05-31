package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.collection.TagCollection

/** Collection変換 */
fun List<Post>.toCollection(): PostCollection {
    return PostCollection(this)
}

class PostCollection(list: List<Post>) : AbstractCollection<Post>(list) {

    /** コメントある投稿のみ抽出 */
    fun hasComments(): PostCollection {
        return PostCollection(filter { !it.comments().isEmpty() })
    }

    /** ソート */
    fun sort(): PostCollection {
        return PostCollection(sortList())
    }

    /** タグ一覧に変換 */
    fun toTags(): TagCollection {
        val tags = list.flatMap { it.tags().list }
        return TagCollection(tags)
    }
}


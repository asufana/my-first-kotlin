package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.collection.TagCollection

/** Collection変換 */
fun List<Post>.toCollection(): PostCollection {
    return PostCollection(this)
}

class PostCollection(list: List<Post>): AbstractCollection<Post>(list) {

    /** フィルタ */
    fun hasComments(): PostCollection {
        val newList = list.filter { it.comments().count() > 0 }.toList();
        return PostCollection(newList)
    }

    /** タグ一覧に変換 */
    fun toTags(): TagCollection {
        val tags = list.flatMap { it.tags().list }
        return TagCollection(tags)
    }
}


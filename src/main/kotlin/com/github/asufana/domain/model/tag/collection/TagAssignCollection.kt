package com.github.asufana.domain.model.tag.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.tag.TagAssign

/** Collection変換 */
fun List<TagAssign>.toCollection(): TagAssignCollection {
    return TagAssignCollection(this)
}

class TagAssignCollection(list: List<TagAssign>) :
        AbstractCollection<TagAssign>(list) {

    /** タグ一覧に変換 */
    fun toTagCollection(): TagCollection {
        val list = this.list.map { it.tag }.toList()
        return list.toCollection()
    }
}

package com.github.asufana.domain.model.tag.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.tag.Tag

/** Collection変換 */
fun List<Tag>.toCollection(): TagCollection {
    return TagCollection(this)
}

class TagCollection(list: List<Tag>) : AbstractCollection<Tag>(list)

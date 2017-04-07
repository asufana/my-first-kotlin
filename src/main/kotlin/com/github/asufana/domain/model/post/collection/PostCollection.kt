package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.post.Post

/** Collection変換 */
fun List<Post>.toCollection(): PostCollection {
    return PostCollection(this)
}

class PostCollection(list: List<Post>): AbstractCollection<Post>(list) {

}

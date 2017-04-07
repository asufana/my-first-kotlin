package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.model.post.Post


class PostCollection(val list: List<Post>) {

    fun count(): Int {
        return list.count()
    }

}

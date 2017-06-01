package com.github.asufana.domain.model.comment.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.comment.Comment

/** Collection変換 */
fun List<Comment>.toCollection(): CommentCollection {
    return CommentCollection(this)
}

class CommentCollection(list: List<Comment>):
        AbstractCollection<CommentCollection, Comment>(list)

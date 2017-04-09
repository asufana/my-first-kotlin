package com.github.asufana.domain

import com.github.asufana.domain.model.comment.vo.CommentName
import com.github.asufana.domain.model.post.vo.PostName
import com.github.asufana.domain.model.tag.vo.TagName

/** テスト用データ */
class T {
    companion object {

        val postName: PostName = PostName("My first post.")
        val commentName: CommentName = CommentName("My first comment.")
        val tagName: TagName = TagName("fun")

    }
}


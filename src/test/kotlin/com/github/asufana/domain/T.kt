package com.github.asufana.domain

import com.github.asufana.domain.model.comment.vo.CommentName
import com.github.asufana.domain.model.post.vo.PostName
import com.github.asufana.domain.model.tag.vo.TagName

/** テスト用データ */
class T {
    companion object {

        val postName01: PostName = PostName("My first post.")
        val postName02: PostName = PostName("My second post.")
        val postName03: PostName = PostName("My third post.")

        val commentName01: CommentName = CommentName("My first comment.")
        val commentName02: CommentName = CommentName("My second comment.")
        val commentName03: CommentName = CommentName("My third comment.")

        val tagName01: TagName = TagName("fun")
        val tagName02: TagName = TagName("etc")
        val tagName03: TagName = TagName("study")

    }
}


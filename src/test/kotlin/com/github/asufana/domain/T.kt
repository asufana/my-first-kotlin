package com.github.asufana.domain

import com.github.asufana.domain.model.comment.vo.CommentName
import com.github.asufana.domain.model.post.vo.PostName

/** テスト用データ */
class T {
    companion object {

        val postName: PostName = PostName("My first post.")
        val commentName: CommentName = CommentName("My first comment.")

    }
}


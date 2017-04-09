package com.github.asufana.domain.model.comment

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.Post
import org.assertj.core.api.Assertions.*
import org.junit.Test


class CommentTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create(): Comment = Comment(Post(T.postName).save(), T.commentName)

        //インスタンス保存
        fun save(): Comment = create().save()
    }

    //インスタンス生成テスト
    @Test
    fun testCreate() {
        //インスタンス生成
        val comment = create()

        //生成されること
        assertThat(comment).isNotNull()
        assertThat(comment.isSaved()).isFalse()
    }

    //インスタンス保存テスト
    @Test
    fun testSave() {
        //インスタンス保存
        val comment = save()

        //保存されること
        assertThat(comment).isNotNull()
        assertThat(comment.isSaved()).isTrue()
    }

    //関連テスト
    @Test
    fun testRelatePost() {
        //インスタンス保存
        val comment = save()

        //関連先Postが取得できること
        val post = comment.post
        assertThat(post).isNotNull()

        //関連先Commentが取得できること
        assertThat(post.comments()).isNotNull()
        assertThat(post.comments().count()).isEqualTo(1)
    }
}

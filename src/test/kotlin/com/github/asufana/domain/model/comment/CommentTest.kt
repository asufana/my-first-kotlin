package com.github.asufana.domain.model.comment

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.PostTest
import org.assertj.core.api.Assertions.*
import org.junit.Test


class CommentTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create01(): Comment = Comment(PostTest.save01(), T.commentName01)
        fun create02(): Comment = Comment(PostTest.save02(), T.commentName02)
        fun create03(): Comment = Comment(PostTest.save03(), T.commentName03)

        //インスタンス保存
        fun save01(): Comment = create01().save()
        fun save02(): Comment = create02().save()
        fun save03(): Comment = create03().save()
    }

    //インスタンス生成テスト
    @Test
    fun testCreate() {
        //インスタンス生成
        val comment = create01()

        //生成されること
        assertThat(comment).isNotNull()
        assertThat(comment.isSaved()).isFalse()
    }

    //インスタンス保存テスト
    @Test
    fun testSave() {
        //インスタンス保存
        val comment = save01()

        //保存されること
        assertThat(comment).isNotNull()
        assertThat(comment.isSaved()).isTrue()
    }

    //関連テスト
    @Test
    fun testRelatePost() {
        //インスタンス保存
        val comment = save01()

        //関連先Postが取得できること
        val post = comment.post
        assertThat(post).isNotNull()

        //関連先Commentが取得できること
        assertThat(post.comments()).isNotNull()
        assertThat(post.comments().count()).isEqualTo(1)
    }
}

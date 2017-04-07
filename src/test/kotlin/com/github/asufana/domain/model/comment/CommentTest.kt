package com.github.asufana.domain.model.comment

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import org.assertj.core.api.Assertions.*
import org.junit.Test


class CommentTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create(): Comment = Comment(T.commentName)
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
}

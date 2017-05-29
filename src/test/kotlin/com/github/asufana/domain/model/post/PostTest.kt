package com.github.asufana.domain.model.post

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.base.entity.UNSAVED_ID
import com.github.asufana.domain.model.post.vo.PostName
import org.assertj.core.api.Assertions.*
import org.junit.Test

class PostTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create01(): Post = Post(T.postName01)
        fun create02(): Post = Post(T.postName02)
        fun create03(): Post = Post(T.postName03)

        //インスタンス保存
        fun save01(): Post = create01().save()
        fun save02(): Post = create02().save()
        fun save03(): Post = create03().save()
    }

    //インスタンス生成テスト
    @Test
    fun testCreate() {
        //インスタンス生成
        val post = create01()

        //生成されること
        assertThat(post).isNotNull()
        assertThat(post.isSaved()).isFalse()

        //未保存時には値が未設定であること
        assertThat(post.id().value).isEqualTo(UNSAVED_ID)
        assertThat(post.version).isEqualTo(UNSAVED_ID)
        assertThat(post.createdDate).isNull()
        assertThat(post.updatedDate).isNull()
    }

    //インスタンス保存テスト
    @Test
    fun testSave() {
        //インスタンス保存
        val post = save01()

        //保存されること
        assertThat(post).isNotNull()
        assertThat(post.isSaved()).isTrue()

        //保存時には値が設定されていること
        assertThat(post.id().value).isNotEqualTo(UNSAVED_ID)
        assertThat(post.version).isNotEqualTo(UNSAVED_ID)
        assertThat(post.createdDate).isNotNull()
        assertThat(post.updatedDate).isNotNull()
    }

    //インスタンス更新テスト
    @Test
    fun testUpdate() {
        //インスタンス保存
        val post = save01()

        //インスタンス更新
        post.name = PostName("Updated name")
        val updated = post.save()

        //生成日付は更新されていないこと
        assertThat(updated.createdDate).isEqualTo(post.createdDate)
        //更新日付は更新されていること
        assertThat(updated.updatedDate).isNotEqualTo(post.updatedDate)
    }
}

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
        fun create(): Post = Post(T.postName)
        //インスタンス保存
        fun save(): Post = create().save()
    }

    //インスタンス生成テスト
    @Test
    fun testCreate() {
        //インスタンス生成
        val post = create()

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
        val post = save()

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
        val post = save()

        //インスタンス更新
        post.name = PostName("Updated name")
        val updated = post.save()

        //生成日付は更新されていないこと
        assertThat(updated.createdDate).isEqualTo(post.createdDate)
        //更新日付は更新されていること
        assertThat(updated.updatedDate).isNotEqualTo(post.updatedDate)
    }
}

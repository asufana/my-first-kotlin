package com.github.asufana.domain.model.tag

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.PostTest
import com.github.asufana.domain.model.tag.vo.TagName
import org.assertj.core.api.Assertions.*
import org.junit.Test

class TagTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create(): Tag = Tag(T.tagName)

        //インスタンス保存
        fun save(): Tag = create().save()
    }

    //インスタンス保存テスト
    @Test
    fun testSave() {
        //インスタンス保存
        val tag = save()

        //保存されること
        assertThat(tag).isNotNull()
        assertThat(tag.isSaved()).isTrue()
    }

    //Postとの関連付けテスト
    @Test
    fun testRelatedPost() {
        //インスタンス保存
        val tag01 = Tag(TagName("fun")).save()
        val tag02 = Tag(TagName("etc")).save()
        val post = PostTest.save()

        //Postと関連付けられていないこと
        assertThat(post.tags().count()).isEqualTo(0)

        //---------------------------------------

        //Postとの関連付け
        val savedPost = post.assign(tag01).assign(tag02)

        //Postと関連付けられていること
        assertThat(savedPost.tags().count()).isEqualTo(2)

        //TagからPostが関連付けられていること
        assertThat(tag01.posts().count()).isEqualTo(1)
        assertThat(tag02.posts().count()).isEqualTo(1)

        //---------------------------------------

        //Postとの関連付け解除
        savedPost.unAssign(tag02)

        //Postと関連付けが解除されていること
        assertThat(savedPost.tags().count()).isEqualTo(1)

        //TagからPostが関連付けられていること
        assertThat(tag01.posts().count()).isEqualTo(1)

        //TagからPostが関連付けが解除されていること
        assertThat(tag02.posts().count()).isEqualTo(0)
    }
}

package com.github.asufana.domain.model.tag

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.exception.EntityException
import com.github.asufana.domain.model.post.PostTest
import org.assertj.core.api.Assertions.*
import org.junit.Test

class TagTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create01(): Tag = Tag(T.tagName01)
        fun create02(): Tag = Tag(T.tagName02)
        fun create03(): Tag = Tag(T.tagName03)

        //インスタンス保存
        fun save01(): Tag = create01().save()
        fun save02(): Tag = create02().save()
        fun save03(): Tag = create03().save()
    }

    //インスタンス保存テスト
    @Test
    fun testSave() {
        //インスタンス保存
        val tag = save01()

        //保存されること
        assertThat(tag).isNotNull()
        assertThat(tag.isSaved()).isTrue()
    }

    //ユニーク制約テスト
    @Test(expected = EntityException::class)
    fun testUnique() {
        save01()
        //重複登録により例外発生すること
        save01()
    }

    //Postとの関連付けテスト
    @Test
    fun testRelatedPost() {
        //インスタンス保存
        val tag01 = TagTest.save01()
        val tag02 = TagTest.save02()
        val post = PostTest.save01()

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

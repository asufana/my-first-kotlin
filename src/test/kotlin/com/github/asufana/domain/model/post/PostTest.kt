package com.github.asufana.domain.model.post

import com.github.asufana.domain.base.UNSAVED_ID
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class PostTest {

    @Autowired
    lateinit var repo: PostRepo

    companion object {
        //インスタンス生成
        fun create(): Post = Post("My first post.")
        //インスタンス保存
        fun save(): Post = create().save()
    }

    @Test
    //インスタンス生成テスト
    fun testCreate() {
        //インスタンス生成
        val post = create()

        //生成されること
        assertThat(post).isNotNull()
        assertThat(post.isSaved()).isFalse()

        //未保存時には値が未設定であること
        assertThat(post.id).isEqualTo(UNSAVED_ID)
    }

    @Test
    //インスタンス保存テスト
    fun testSave() {
        //インスタンス保存
        val post = save()

        //保存されること
        assertThat(post).isNotNull()
        assertThat(post.isSaved()).isTrue()

        //保存時には値が設定されていること
        assertThat(post.id).isNotEqualTo(UNSAVED_ID)
    }
}

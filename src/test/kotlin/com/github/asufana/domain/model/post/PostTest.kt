package com.github.asufana.domain.model.post

import org.assertj.core.api.Assertions.*
import org.junit.Test

class PostTest {

    companion object {
        //インスタンス生成
        fun create(): Post = Post("My first post.")
    }

    @Test
    //インスタンス生成テスト
    fun testCreate() {
        //インスタンス生成
        val post = PostTest.create()

        //生成されること
        assertThat(post).isNotNull()
    }
}

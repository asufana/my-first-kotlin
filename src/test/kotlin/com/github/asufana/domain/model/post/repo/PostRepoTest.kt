package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.PostTest
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test

class PostRepoTest : AbstractTest() {

    @Before
    fun setup() {
        //DB保存件数がゼロ件であること
        assertThat(PostRepo.count()).isEqualTo(0)
    }

    @Test
    fun testSave() {
        //エンティティ保存
        PostRepo.save(PostTest.create01())

        //DB保存件数が１件であること
        assertThat(PostRepo.count()).isEqualTo(1)
    }

    @Test
    fun testFindAll() {
        //エンティティ保存
        val saved01 = PostTest.save01()
        val saved02 = PostTest.save02()

        //DB保存件数が２件であること
        val collection = PostRepo.findAll()
        assertThat(collection.count()).isEqualTo(2)
        assertThat(collection.list).contains(saved01, saved02)
    }

    @Test
    fun testFindOne() {
        //エンティティ保存
        val saved = PostTest.save01()

        //保存したエンティティが取得できること
        val found = PostRepo.findOne(saved.id())
        assertThat(found).isEqualTo(saved)
    }
}

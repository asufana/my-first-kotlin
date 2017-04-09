package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.PostTest
import org.assertj.core.api.Assertions.*
import org.junit.Test

class PostRepoTest : AbstractTest() {

    //エンティティ取得テスト
    @Test
    fun testSave() {

        //DB保存件数がゼロ件であること
        assertThat(postRepo.count()).isEqualTo(0)

        //エンティティ保存
        postRepo.saveAndFlush(PostTest.create())

        //DB保存件数が１件であること
        assertThat(postRepo.count()).isEqualTo(1)
    }
}

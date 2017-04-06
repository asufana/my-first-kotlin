package com.github.asufana.domain.model.post.repo

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.PostTest
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class PostRepoTest : AbstractTest() {

    @Autowired
    lateinit var repo: PostRepo

    @Test
    //エンティティ取得テスト
    fun testSave() {

        //DB保存件数がゼロ件であること
        assertThat(repo.count()).isEqualTo(0)

        //エンティティ保存
        repo.saveAndFlush(PostTest.create())

        //DB保存件数が１件であること
        assertThat(repo.count()).isEqualTo(1)
    }
}

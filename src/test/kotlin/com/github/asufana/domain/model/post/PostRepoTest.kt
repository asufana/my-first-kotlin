package com.github.asufana.domain.model.post

import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class PostRepoTest {

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

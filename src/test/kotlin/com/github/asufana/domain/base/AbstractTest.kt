package com.github.asufana.domain.base

import com.github.asufana.domain.model.comment.repo.CommentRepoBase
import com.github.asufana.domain.model.post.repo.PostRepo
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
//テスト毎にDBをクリアする
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
abstract class AbstractTest {

    @Autowired lateinit var postRepo: PostRepo
    @Autowired lateinit var commentRepo: CommentRepoBase

}

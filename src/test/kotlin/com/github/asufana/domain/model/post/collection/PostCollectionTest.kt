package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.repo.PostRepo
import com.github.asufana.domain.model.post.vo.PostName
import org.assertj.core.api.Assertions.*
import org.junit.Test

class PostCollectionTest : AbstractTest() {

    //コレクションテスト
    @Test
    fun testCount() {
        Post(PostName("01")).save()
        Post(PostName("02")).save()
        Post(PostName("03")).save()

        val list = PostRepo.findAll()
        assertThat(list is PostCollection).isTrue()
        assertThat(list.count()).isEqualTo(3)
    }

}

package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.post.PostTest
import com.github.asufana.domain.model.post.repo.PostRepo
import org.assertj.core.api.Assertions.*
import org.junit.Test

class PostCollectionTest : AbstractTest() {

    @Test
    fun testCount() {
        PostTest.save01()
        PostTest.save02()
        PostTest.save03()

        val list = PostRepo.findAll()
        assertThat(list is PostCollection).isTrue()
        assertThat(list.count()).isEqualTo(3)
    }

}

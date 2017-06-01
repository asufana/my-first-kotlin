package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.model.comment.CommentTest
import com.github.asufana.domain.model.post.PostTest
import com.github.asufana.domain.model.post.repo.PostRepo
import com.github.asufana.domain.model.tag.TagTest
import com.github.asufana.domain.model.tag.collection.TagCollection
import org.assertj.core.api.Assertions.*
import org.junit.Test

class PostCollectionTest : AbstractTest() {

    @Test
    fun testCount() {
        PostTest.save01()
        PostTest.save02()
        PostTest.save03()

        val posts = PostRepo.findAll()
        assertThat(posts is PostCollection).isTrue()
        assertThat(posts.count()).isEqualTo(3)
    }

    @Test
    fun testHasCommentAndTag() {
        val comment = CommentTest.save01()
        val tag = TagTest.save01()
        comment.post.assign(tag).save()

        val posts = PostRepo.findAll()
        val tags = posts.hasComments().toTags().sort()
        assertThat(tags is TagCollection).isTrue()
        assertThat(tags.count()).isEqualTo(1)
    }

}

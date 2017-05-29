package com.github.asufana.domain.model.tag

import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.exception.EntityException
import com.github.asufana.domain.model.post.PostTest
import org.junit.Test

class TagAssignTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create01(): TagAssign = TagAssign(PostTest.save01(), TagTest.save01())
        fun create02(): TagAssign = TagAssign(PostTest.save02(), TagTest.save02())
        fun create03(): TagAssign = TagAssign(PostTest.save03(), TagTest.save03())

        //インスタンス保存
        fun save01(): TagAssign = create01().save()
        fun save02(): TagAssign = create02().save()
        fun save03(): TagAssign = create03().save()
    }

    //ユニーク制約テスト
    @Test(expected = EntityException::class)
    fun testUnique() {
        //インスタンス保存
        val tagAssign = save01()

        //重複登録により例外発生すること
        TagAssign(tagAssign.post, tagAssign.tag).save()
    }
}

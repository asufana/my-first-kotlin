package com.github.asufana.domain.model.tag

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import com.github.asufana.domain.exception.EntityException
import com.github.asufana.domain.model.post.Post
import org.junit.Test

class TagAssignTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create(): TagAssign = TagAssign(
                Post(T.postName).save(),
                Tag(T.tagName).save())

        //インスタンス保存
        fun save(): TagAssign = create().save()
    }

    //ユニーク制約テスト
    @Test(expected = EntityException::class)
    fun testUnique() {
        //インスタンス保存
        val tagAssign = save()

        //重複登録により例外発生すること
        TagAssign(tagAssign.post, tagAssign.tag).save()
    }
}

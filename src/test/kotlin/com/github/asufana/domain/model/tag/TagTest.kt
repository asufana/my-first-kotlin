package com.github.asufana.domain.model.tag

import com.github.asufana.domain.T
import com.github.asufana.domain.base.AbstractTest
import org.assertj.core.api.Assertions.*
import org.junit.Test

class TagTest : AbstractTest() {

    companion object {
        //インスタンス生成
        fun create(): Tag = Tag(T.tagName)

        //インスタンス保存
        fun save(): Tag = create().save()
    }

    //インスタンス保存テスト
    @Test
    fun testSave() {
        //インスタンス保存
        val tag = save()

        //保存されること
        assertThat(tag).isNotNull()
        assertThat(tag.isSaved()).isTrue()
    }
}

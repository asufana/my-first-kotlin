package com.github.asufana.domain.model.tag.repo

import com.github.asufana.domain.base.repo.AbstractRepo
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.Tag
import com.github.asufana.domain.model.tag.TagAssign
import com.github.asufana.domain.model.tag.collection.TagCollection
import com.github.asufana.domain.model.tag.collection.toCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
private interface TagAssignRepoBase : JpaRepository<TagAssign, Long>

@Component
class TagAssignRepo : AbstractRepo() {

    @Autowired
    private lateinit var repo: TagAssignRepoBase

    fun findBy(post: Post, tag: Tag): TagAssign? {
        val list = em
                .createQuery("from TagAssign where " +
                        "post = :post and tag = :tag", TagAssign::class.java)
                .setParameter("post", post)
                .setParameter("tag", tag)
                .resultList
        return if (list.isEmpty()) null else list[0]
    }

    /** タグ付け */
    fun assign(post: Post, tag: Tag): TagAssign {
        val tagAssign = findBy(post, tag)
        if (tagAssign != null) {
            return tagAssign
        }
        return TagAssign(post, tag).save()
    }

    /** タグ付け解除 */
    fun unAssign(post: Post, tag: Tag) {
        val tagAssign = findBy(post, tag)
        tagAssign?.delete()
    }

    /** タグ一覧の取得 */
    internal fun findBy(post: Post): TagCollection {
        val tagAssignList = em
                .createQuery("from TagAssign where post = :post", TagAssign::class.java)
                .setParameter("post", post)
                .resultList
                .toCollection()
        return tagAssignList.toTagCollection()
    }

    fun save(tagAssign: TagAssign): TagAssign {
        return repo.saveAndFlush(tagAssign)
    }

    fun delete(tagAssign: TagAssign) {
        return repo.delete(tagAssign)
    }
}

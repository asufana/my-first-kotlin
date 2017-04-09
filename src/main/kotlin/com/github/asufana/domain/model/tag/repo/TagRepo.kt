package com.github.asufana.domain.model.tag.repo

import com.github.asufana.domain.base.repo.AbstractRepo
import com.github.asufana.domain.model.tag.Tag
import com.github.asufana.domain.model.tag.vo.TagName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
private interface TagRepoBase : JpaRepository<Tag, Long>

@Component
class TagRepo : AbstractRepo() {

    @Autowired
    private lateinit var repo: TagRepoBase

    fun findBy(name: TagName): Tag? {
        val list = em
                .createQuery("from Tag where name = :name", Tag::class.java)
                .setParameter("name", name)
                .resultList
        return if (list.isEmpty()) null else list[0]
    }

    fun save(tag: Tag): Tag {
        return repo.saveAndFlush(tag)
    }
}

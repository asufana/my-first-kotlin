package com.github.asufana.domain.model.tag.repo

import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.tag.Tag
import com.github.asufana.domain.model.tag.vo.TagName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
private interface TagRepoBase : JpaRepository<Tag, Long>

class TagRepo {

    companion object {
        private fun repo(): TagRepoBase = resolve(TagRepoBase::class.java)
        private fun em(): EntityManager = resolve(EntityManager::class.java)

        fun findBy(name: TagName): Tag? {
            val list = em()
                    .createQuery("from Tag where name = :name", Tag::class.java)
                    .setParameter("name", name)
                    .resultList
            return if (list.isEmpty()) null else list[0]
        }

        fun save(tag: Tag): Tag {
            return repo().saveAndFlush(tag)
        }
    }

}

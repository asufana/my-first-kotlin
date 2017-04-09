package com.github.asufana.domain.model.tag.repo

import com.github.asufana.domain.model.tag.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
private interface TagRepoBase : JpaRepository<Tag, Long>

@Component
class TagRepo {

    @Autowired
    private lateinit var repo: TagRepoBase

    fun save(tag: Tag): Tag {
        return repo.saveAndFlush(tag)
    }
}

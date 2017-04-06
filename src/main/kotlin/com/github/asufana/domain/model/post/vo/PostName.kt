package com.github.asufana.domain.model.post.vo

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PostName(
        //Jpaのためにデフォルト値が必要
        @Column(name = "name", nullable = false)
        val value: String = ""
)

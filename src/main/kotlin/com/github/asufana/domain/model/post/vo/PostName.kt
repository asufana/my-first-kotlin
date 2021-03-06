package com.github.asufana.domain.model.post.vo

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PostName(
        @Column(name = "name", nullable = false)
        val value: String = ""
)

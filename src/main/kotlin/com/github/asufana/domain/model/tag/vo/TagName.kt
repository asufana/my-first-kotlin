package com.github.asufana.domain.model.tag.vo

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class TagName(
        @Column(name = "name", nullable = false)
        val value: String = ""
)


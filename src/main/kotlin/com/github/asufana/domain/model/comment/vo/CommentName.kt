package com.github.asufana.domain.model.comment.vo

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class CommentName(
        @Column(name = "name", nullable = false)
        val value: String = ""
)

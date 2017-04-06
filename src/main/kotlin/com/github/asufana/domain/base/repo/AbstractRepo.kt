package com.github.asufana.domain.base.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface AbstractRepo<T>: JpaRepository<T, Long>

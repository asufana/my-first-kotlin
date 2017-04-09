package com.github.asufana.domain.base.repo

import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager


abstract class AbstractRepo {

    @Autowired
    protected lateinit var em: EntityManager

}

package com.github.asufana.domain.exception

const private val UNIQUE_ERROR_MESSAGE: String = "エンティティ重複しています"

class EntityException(message: String) : RuntimeException(message) {

    companion object {
        //ユニーク制約エラー
        fun uniqueConstraints(): EntityException = EntityException(UNIQUE_ERROR_MESSAGE)
    }

}

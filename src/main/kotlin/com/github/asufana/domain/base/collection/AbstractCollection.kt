package com.github.asufana.domain.base.collection

import com.github.asufana.domain.base.entity.AbstractEntity


abstract class AbstractCollection<out T: AbstractEntity>(val list: List<T>) {

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    fun count(): Int {
        return list.count()
    }

    fun get(index: Int): T {
        return list[index]
    }

    protected fun filter(predicate: (T) -> Boolean): List<T> {
        return list.filter(predicate).toList()
    }

    protected fun sortList(): List<T> {
        return list.sortedBy { it.createdDate }
    }

    protected fun reverse(): List<T> {
        return list.reversed()
    }

}

package com.github.asufana.domain.base.collection

import com.github.asufana.domain.base.entity.AbstractEntity
import java.lang.reflect.ParameterizedType




abstract class AbstractCollection<out S: AbstractCollection<S, T>, out T: AbstractEntity>(val list: List<T>): Iterable<T> {

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    fun count(): Int {
        return list.count()
    }

    fun get(index: Int): T? {
        return if (count() > index) {
            return list[index]
        }
        else {
            return null
        }
    }

    fun sort(): S {
        val newList = list.sortedBy { it.createdDate }
        return toCollection(newList)
    }

    fun reverse(): S {
        val newList = list.reversed()
        return toCollection(newList)
    }

    protected fun filter(predicate: (T) -> Boolean): S {
        val newList = list.filter(predicate).toList()
        return toCollection(newList)
    }

    protected fun <T> toCollection(list: List<T>): S {
        val klass = this.javaClass
        val type = klass.genericSuperclass as ParameterizedType
        val arg = type.actualTypeArguments[0]
        val collectionClass = if (arg is ParameterizedType) {
            arg.rawType as Class<T>
        }
        else {
            arg as Class<T>
        }
        val constructor = collectionClass.getConstructor(List::class.java)
        return constructor.newInstance(list) as S
    }

    override fun iterator(): Iterator<T> {
        return list.iterator()
    }
}

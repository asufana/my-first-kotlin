package com.github.asufana.domain.base

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/** 未保存Entity時のID値 */
const val UNSAVED_ID: Long = -1L

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = UNSAVED_ID

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other == null) return false
        if (other.javaClass != this.javaClass) return false
        if (other is BaseEntity) {
            return sameIdentityAs(other)
        }
        return false
    }

    protected fun sameIdentityAs(other: BaseEntity): Boolean {
        if (this.id == UNSAVED_ID || other.id == UNSAVED_ID) {
            return false
        }
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return if (this.id != UNSAVED_ID) this.id.hashCode() else 0
    }

}

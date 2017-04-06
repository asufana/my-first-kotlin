package com.github.asufana.domain.base

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Version

private const val ENTITY_ENABLE: Int = 1
private const val ENTITY_DISABLE: Int = 0

@MappedSuperclass
abstract class AbstractEntity : BaseEntity() {

    /** 楽観ロック値 */
    @Version
    val version: Long = UNSAVED_ID

    /** 有効フラグ */
    @Column(name = "is_enable", nullable = false)
    private var isEnable: Int = ENTITY_ENABLE

    fun isEnable(): Boolean {
        return this.isEnable == ENTITY_ENABLE
    }

    fun enable() {
        this.isEnable = ENTITY_ENABLE
    }

    fun disable() {
        this.isEnable = ENTITY_DISABLE
    }

    /** 生成日時 */
    @Column(name = "created_date", nullable = false)
    var createdDate: LocalDateTime? = null
        private set

    /** 更新日時 */
    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime? = null
        private set

    /** 生成時イベント */
    @PrePersist
    protected fun prePersist() {
        this.createdDate = LocalDateTime.now()
        this.updatedDate = this.createdDate;
    }

    /** 更新時イベント */
    @PreUpdate
    protected fun preUpdate() {
        this.updatedDate = LocalDateTime.now()
    }

    /** エンティティが仕様を満たしているかどうか */
    abstract fun isSatisfied()
}

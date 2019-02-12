package com.grokonez.uploadmultifiles.model.audit;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
 
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * - @EntityListeners注释用于指定回调侦听器类。我们使用Spring Data的JPA实体监听器类AuditingEntityListener。
 * - @MappedSuperClass注释用于将属性移动到父类DateAudit，该类将由所有审计实体扩展。
 */
@MappedSuperclass
// 使用审计功能，首先申明实体类，需要在类上加上注解@EntityListeners(AuditingEntityListener.class)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class DateAudit {

    /**
     * <pre>@CreatedDate</pre>表示该字段为创建时间时间字段，在这个实体被insert的时候，会设置值
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
 
    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
 
    public Instant getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
 
    public Instant getUpdatedAt() {
        return updatedAt;
    }
 
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
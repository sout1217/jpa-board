package com.example.demo.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // MEMO: 2022/10/23 상속한 엔티티들은 부모클래스의 필드들을 컬럼으로 인식한다
@EntityListeners(AuditingEntityListener.class) // Listener 활성화
public abstract class AbstractBaseEntity {

    @CreatedDate
    protected LocalDateTime createdAt;

    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    protected String lastModifiedBy;

    @CreatedBy
    protected String createdBy;

}

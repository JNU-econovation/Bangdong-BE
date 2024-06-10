package com.econovation.bangdong.common.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Timestamp;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class, SoftDeleteListener.class})
public class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Timestamp updatedDate;

    @Builder.Default
    @Column(nullable = false)
    private boolean isDeleted = false;

    public void delete() { this.isDeleted = true; }

}

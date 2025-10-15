package com.kakaotechbootcamp.community.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.Clock;
import java.time.Instant;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {

    @Column(nullable = false)
    @CreatedDate
    protected Instant createdAt;
    protected Instant deletedAt;

    public void deleteOn(Clock clock) {
        deletedAt = Instant.now(clock);
    }
}

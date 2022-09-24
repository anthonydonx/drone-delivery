package com.musala.dronedelivery.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

/**
 * @author anthonydonx
 * few  meta information for each record
 * @MappedSuperclass only models inheritance in the OOP world.
 * From a database perspective, the @MappedSuperclass inheritance model is invisible since all the base class properties are
 * simply copied to the database table mapped by the actual entity class.
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    protected Instant modifiedAt;

    @Column
    @Version
    protected int version;

}

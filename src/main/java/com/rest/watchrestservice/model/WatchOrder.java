package com.rest.watchrestservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "watch_order")
public class WatchOrder {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    private Customer customer;

    @Column
    private String customer_ref;

    @OneToMany(mappedBy = "order")
    private Set<WatchOrderLine> orderLines;

    @Column
    @JdbcType(BigIntJdbcType.class)
    private BigInteger version;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

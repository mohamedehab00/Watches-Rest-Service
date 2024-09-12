package com.rest.watchrestservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    private UUID id;

    @Column
    private String customer_ref;

    @Column
    private UUID customer_id;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "watch_order_id")
    private Set<WatchOrderLine> orderLines = new HashSet<>();

    @Column
    @JdbcType(BigIntJdbcType.class)
    private BigInteger version;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "watch_order_shipment_id")
    private WatchOrderShipment orderShipment;
}

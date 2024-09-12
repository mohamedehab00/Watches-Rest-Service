package com.rest.watchrestservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @UuidGenerator
    private UUID id;
    @Column
    private String name;
    @Column
    private Integer version;
    @CreationTimestamp
    private LocalDateTime addedAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder.Default
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private List<WatchOrder> orders = new ArrayList<>();
}

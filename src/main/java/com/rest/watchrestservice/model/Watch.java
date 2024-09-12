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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "watch")
public class Watch {
    @Id
    @UuidGenerator
    private UUID id;
    @Column
    private String model;
    @Column
    private String serialNumber;
    @Column
    private String origin;
    @Column
    private Double price;
    @Column(name = "quantity_on_hand")
    private Integer quantityOnHand;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "watch_id")
    private Set<WatchOrderLine> orderLines = new HashSet<>();

    @Builder.Default
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinTable(
            name = "watch_category",
            joinColumns = @JoinColumn(name = "watch_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories = new HashSet<>();
}

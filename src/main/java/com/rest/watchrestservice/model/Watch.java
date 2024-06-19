package com.rest.watchrestservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "watch")
public class Watch {
    @Id
    @UuidGenerator
    private String id;
    @Column
    private String model;
    @Column
    private String serialNumber;
    @Column
    private String origin;
    @Column
    private Double price;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

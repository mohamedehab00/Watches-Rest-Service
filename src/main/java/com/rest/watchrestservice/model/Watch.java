package com.rest.watchrestservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
//@Entity
//@Table(name = "watch")
public class Watch {
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.UUID)
  //  @Column
    private UUID id;
    //@Column
    private String model;
    //@Column
    private String serialNumber;
    //@Column
    private String origin;
    //@Column
    private Double price;
    //@Column
    private LocalDateTime addedAt;
}

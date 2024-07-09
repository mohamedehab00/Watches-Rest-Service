package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Builder
@Data
public class WatchOrderLineCreationDto {
    private UUID watchId;
    private Integer order_quantity;
    private Integer quantity_allocated;
    private BigInteger version;
}

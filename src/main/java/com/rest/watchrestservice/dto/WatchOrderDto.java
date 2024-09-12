package com.rest.watchrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WatchOrderDto {
    private UUID id;

    private UUID customer_id;

    private Set<WatchOrderLineDto> orderLines;

    private LocalDateTime updatedAt;

    private WatchOrderShipmentInfoDto orderShipment;
}

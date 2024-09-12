package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class WatchOrderInfoDto {
    private UUID id;

    private UUID customer_id;

    private Set<WatchOrderLineDto> orderLines;

    private LocalDateTime updatedAt;
}

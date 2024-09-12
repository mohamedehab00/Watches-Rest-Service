package com.rest.watchrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WatchOrderLineDto {
    private UUID id;

    private UUID watch_id;

    private UUID watch_order_id;

    private LocalDateTime updatedAt;
}

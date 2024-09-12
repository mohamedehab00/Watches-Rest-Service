package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WatchOrderShipmentDto {
    private UUID id;
    private String tracking_number;
    private WatchOrderInfoDto order;
}

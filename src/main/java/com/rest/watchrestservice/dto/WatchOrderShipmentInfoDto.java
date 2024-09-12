package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WatchOrderShipmentInfoDto {
    private UUID id;
    private String tracking_number;
}

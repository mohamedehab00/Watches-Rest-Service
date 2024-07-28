package com.rest.watchrestservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WatchOrderShipmentCreationDto {
    private String tracking_number;
    private int version;
}

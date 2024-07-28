package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class WatchOrderCreationDto {
    private UUID customerId;
    private String customerRef;
    private Set<WatchOrderLineCreationDto> orderLines;
    private BigInteger version;
    private WatchOrderShipmentCreationDto shipment;
}

package com.rest.watchrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WatchDto {
    private UUID id;
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
    private Integer quantityOnHand;
    private Set<WatchOrderLineDto> orderLines;
    private Set<CategoryInfoDto> categories;
}

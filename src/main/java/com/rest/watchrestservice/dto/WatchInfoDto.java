package com.rest.watchrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WatchInfoDto {
    private UUID id;
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
    private Integer quantityOnHand;
}

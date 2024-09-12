package com.rest.watchrestservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WatchCreationDto {
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
    private Integer quantityOnHand;
    private List<UUID> categories;
}

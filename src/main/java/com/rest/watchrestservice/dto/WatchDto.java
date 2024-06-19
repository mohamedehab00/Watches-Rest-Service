package com.rest.watchrestservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WatchDto {
    private String id;
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
}

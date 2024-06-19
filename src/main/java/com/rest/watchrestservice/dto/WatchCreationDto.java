package com.rest.watchrestservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class WatchCreationDto {
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
}

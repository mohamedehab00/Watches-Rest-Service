package com.rest.watchrestservice.dto;

import com.rest.watchrestservice.model.Category;
import com.rest.watchrestservice.model.WatchOrderLine;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class WatchDto {
    private UUID id;
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
    private Integer quantityOnHand;
    private Set<WatchOrderLine> orderLines;
    private Set<Category> categories;
}

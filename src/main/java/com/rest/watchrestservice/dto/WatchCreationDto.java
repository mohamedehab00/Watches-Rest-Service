package com.rest.watchrestservice.dto;

import com.rest.watchrestservice.model.Category;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class WatchCreationDto {
    private String model;
    private String serialNumber;
    private String origin;
    private Double price;
    private Integer quantityOnHand;
    private List<UUID> Categories;
}

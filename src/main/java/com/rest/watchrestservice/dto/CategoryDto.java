package com.rest.watchrestservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryDto {
    private UUID id;
    private String description;
    private Integer version;
}

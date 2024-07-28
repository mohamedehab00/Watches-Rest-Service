package com.rest.watchrestservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryCreationDto {
    private String description;
    private Integer version;
}

package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryInfoDto {
    private UUID id;
    private String description;
}

package com.rest.watchrestservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreationDto {
    private String name;
    private int version;
}

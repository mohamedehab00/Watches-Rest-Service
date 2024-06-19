package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private String id;
    private String name;
    private int version;
}

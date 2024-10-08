package com.rest.watchrestservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CustomerDto {
    private UUID id;
    private String name;
    private List<WatchOrderDto> orders;
}

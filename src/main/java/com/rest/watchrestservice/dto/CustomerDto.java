package com.rest.watchrestservice.dto;

import com.rest.watchrestservice.model.WatchOrder;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CustomerDto {
    private UUID id;
    private String name;
    private int version;
    private Set<WatchOrder> orders;
}

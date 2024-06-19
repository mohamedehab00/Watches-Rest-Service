package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;

import java.util.List;
import java.util.UUID;

public interface WatchService {
    WatchDto getWatchById(String id);

    List<WatchDto> listWatches();

    WatchDto addWatch(WatchCreationDto watch);

    WatchDto updateById(String id, WatchCreationDto watch);

    void deleteById(String id);
}

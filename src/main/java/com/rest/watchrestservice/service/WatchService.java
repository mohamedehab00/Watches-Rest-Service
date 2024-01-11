package com.rest.watchrestservice.service;

import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;

import java.util.List;
import java.util.UUID;

public interface WatchService {
    Watch getWatchById(UUID id);

    List<Watch> listWatches();

    Watch addWatch(Watch watch);

    Watch updateById(UUID id, Watch watch);

    void deleteById(UUID id);
}

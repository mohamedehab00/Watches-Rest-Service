package com.rest.watchrestservice.service;

import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;

import java.util.List;
import java.util.UUID;

public interface WatchService {
    Watch getWatchById(String id);

    List<Watch> listWatches();

    Watch addWatch(Watch watch);

    Watch updateById(String id, Watch watch);

    void deleteById(String id);
}

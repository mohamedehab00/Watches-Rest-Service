package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.service.WatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class WatchServiceImpl implements WatchService {
    private Map<String,Watch> watchMap;

    public WatchServiceImpl() {

    }

    @Override
    public Watch getWatchById(String id) {
        return watchMap.get(id);
    }

    @Override
    public List<Watch> listWatches() {
        return watchMap.values().stream().toList();
    }

    @Override
    public Watch addWatch(Watch watch) {
        return new Watch();
    }

    @Override
    public Watch updateById(String id, Watch watch) {
        Watch existingWatch = this.watchMap.get(id);

        if (watch.getModel() != null)
            existingWatch.setModel(watch.getModel());
        if (watch.getPrice() != null)
            existingWatch.setPrice(watch.getPrice());
        if (watch.getOrigin() != null)
            existingWatch.setOrigin(watch.getOrigin());
        if (watch.getSerialNumber() != null)
            existingWatch.setSerialNumber(watch.getSerialNumber());

        this.watchMap.put(id,existingWatch);

        return existingWatch;
    }

    @Override
    public void deleteById(String id) {
        this.watchMap.remove(id);
    }
}

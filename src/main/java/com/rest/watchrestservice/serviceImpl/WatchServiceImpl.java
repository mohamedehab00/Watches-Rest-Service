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
    private Map<UUID,Watch> watchMap;

    public WatchServiceImpl() {
        this.watchMap = new HashMap<>();

        Watch watch1 = Watch.builder()
                .id(UUID.randomUUID())
                .model("Rolex")
                .serialNumber("1548795132")
                .origin("Holland")
                .price(14000.0)
                .addedAt(LocalDateTime.now())
                .build();
        Watch watch2 = Watch.builder()
                .id(UUID.randomUUID())
                .model("Casio")
                .serialNumber("1531295132")
                .origin("Germany")
                .price(17000.0)
                .addedAt(LocalDateTime.now())
                .build();
        Watch watch3 = Watch.builder()
                .id(UUID.randomUUID())
                .model("TAG")
                .serialNumber("1548975632")
                .origin("Italy")
                .price(8000.0)
                .addedAt(LocalDateTime.now())
                .build();

        this.watchMap.put(watch1.getId(),watch1);
        this.watchMap.put(watch2.getId(),watch2);
        this.watchMap.put(watch3.getId(),watch3);
    }

    @Override
    public Watch getWatchById(UUID id) {
        return watchMap.get(id);
    }

    @Override
    public List<Watch> listWatches() {
        return watchMap.values().stream().toList();
    }

    @Override
    public Watch addWatch(Watch watch) {
        UUID id = UUID.randomUUID();

        while (this.watchMap.containsKey(id)){
            id = UUID.randomUUID();
        }

        Watch newWatch = Watch.builder()
                .id(id)
                .model(watch.getModel())
                .serialNumber(watch.getSerialNumber())
                .origin(watch.getOrigin())
                .price(watch.getPrice())
                .addedAt(LocalDateTime.now())
                .build();

        return newWatch;
    }

    @Override
    public Watch updateById(UUID id, Watch watch) {
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
    public void deleteById(UUID id) {
        this.watchMap.remove(id);
    }
}

package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.repository.WatchRepository;
import com.rest.watchrestservice.service.WatchService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WatchServiceImpl implements WatchService {
    private WatchRepository repository;

    @Autowired
    public void setRepository(WatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Watch getWatchById(String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Watch> listWatches() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Watch addWatch(Watch watch) {
        watch = repository.save(watch);

        return watch;
    }

    @Override
    @Transactional
    public Watch updateById(String id, Watch watch) {
        Watch existingWatch = repository.findById(id).orElseThrow(RuntimeException::new);

        if (watch.getModel() != null)
            existingWatch.setModel(watch.getModel());
        if (watch.getPrice() != null)
            existingWatch.setPrice(watch.getPrice());
        if (watch.getOrigin() != null)
            existingWatch.setOrigin(watch.getOrigin());
        if (watch.getSerialNumber() != null)
            existingWatch.setSerialNumber(watch.getSerialNumber());

        existingWatch = repository.save(existingWatch);

        return existingWatch;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
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
    private Mapper mapper;

    @Autowired
    public void setRepository(WatchRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public WatchDto getWatchById(String id) {
        Watch watch = repository.findById(id).orElseThrow(RuntimeException::new);

        return mapper.watchToWatchDto(watch);
    }

    @Override
    public List<WatchDto> listWatches() {
        return repository.findAll().stream().map(mapper::watchToWatchDto).toList();
    }

    @Override
    @Transactional
    public WatchDto addWatch(WatchCreationDto watchCreationDto) {

        Watch createdWatch = repository.save(mapper.watchCreationDtoToWatch(watchCreationDto));

        return mapper.watchToWatchDto(createdWatch);
    }

    @Override
    @Transactional
    public WatchDto updateById(String id, WatchCreationDto watch) {
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

        return mapper.watchToWatchDto(existingWatch);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

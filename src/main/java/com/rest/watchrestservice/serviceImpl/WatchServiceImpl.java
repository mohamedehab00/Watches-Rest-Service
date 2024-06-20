package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.UtilClass;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.exceptions.ElementNotFoundException;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.repository.WatchRepository;
import com.rest.watchrestservice.service.WatchService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public WatchDto getWatchById(UUID id) {
        Optional<Watch> optionalExistingWatch = repository.findById(id);

        if (optionalExistingWatch.isEmpty()){
            throw new ElementNotFoundException(STR."Watch with id: \{id} is not found");
        }

        Watch watch = optionalExistingWatch.get();

        return mapper.watchToWatchDto(watch);
    }

    @Override
    public List<WatchDto> listWatches(String model, String origin, Integer size, Integer page) {
        Page<Watch> watches;

        PageRequest pageRequest = UtilClass.buildPageRequest(size, page);

        if (StringUtils.hasText(model) && StringUtils.hasText(origin)){
            watches = repository.findWatchesByModelLikeAndOriginLike(STR."%\{model}%",STR."%\{origin}%", pageRequest);

            return new PageImpl<>(watches.stream().map(mapper::watchToWatchDto).toList()).getContent();
        } else if (StringUtils.hasText(model)) {
            watches = repository.findWatchesByModelLike(STR."%\{model}%", pageRequest);

            return new PageImpl<>(watches.stream().map(mapper::watchToWatchDto).toList()).getContent();
        } else if (StringUtils.hasText(origin)) {
            watches = repository.findWatchesByOriginLike(STR."%\{origin}%", pageRequest);

            return new PageImpl<>(watches.stream().map(mapper::watchToWatchDto).toList()).getContent();
        } else {
            watches = repository.findAll(pageRequest);

            return new PageImpl<>(watches.stream().map(mapper::watchToWatchDto).toList()).getContent();
        }
    }

    @Override
    @Transactional
    public WatchDto addWatch(WatchCreationDto watchCreationDto) {

        Watch createdWatch = repository.save(mapper.watchCreationDtoToWatch(watchCreationDto));

        return mapper.watchToWatchDto(createdWatch);
    }

    @Override
    @Transactional
    public WatchDto updateById(UUID id, WatchCreationDto watch) {
        Optional<Watch> optionalExistingWatch = repository.findById(id);

        if (optionalExistingWatch.isEmpty()){
            throw new ElementNotFoundException(STR."Watch with id: \{id} is not found");
        }

        Watch existingWatch = optionalExistingWatch.get();

        if (watch.getModel() != null)
            existingWatch.setModel(watch.getModel());
        if (watch.getPrice() != null)
            existingWatch.setPrice(watch.getPrice());
        if (watch.getOrigin() != null)
            existingWatch.setOrigin(watch.getOrigin());
        if (watch.getSerialNumber() != null)
            existingWatch.setSerialNumber(watch.getSerialNumber());
        if (watch.getQuantityOnHand() != null)
            existingWatch.setQuantityOnHand(watch.getQuantityOnHand());

        existingWatch = repository.save(existingWatch);

        return mapper.watchToWatchDto(existingWatch);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}

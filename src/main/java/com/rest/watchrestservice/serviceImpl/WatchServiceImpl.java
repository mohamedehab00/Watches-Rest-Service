package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.UtilClass;
import com.rest.watchrestservice.dto.CategoryCreationDto;
import com.rest.watchrestservice.dto.CategoryDto;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.exceptions.ElementNotFoundException;
import com.rest.watchrestservice.model.Category;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.repository.CategoryRepository;
import com.rest.watchrestservice.repository.WatchRepository;
import com.rest.watchrestservice.service.WatchService;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Data
@Service
public class WatchServiceImpl implements WatchService {
    private WatchRepository watchRepository;
    private CategoryRepository categoryRepository;

    private Mapper mapper;

    @Autowired
    public void setWatchRepository(WatchRepository repository) {
        this.watchRepository = repository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public WatchDto getWatchById(UUID id) {
        Optional<Watch> optionalExistingWatch = getWatchRepository().findById(id);

        if (optionalExistingWatch.isEmpty()){
            throw new ElementNotFoundException(STR."Watch with id: \{id} is not found");
        }

        Watch watch = optionalExistingWatch.get();

        return getMapper().watchToWatchDto(watch);
    }

    @Override
    public List<WatchDto> listWatches(String model, String origin, Integer size, Integer page) {
        Page<Watch> watches;

        PageRequest pageRequest = UtilClass.buildPageRequest(size, page, Sort.by(Sort.Order.asc("model")));

        if (StringUtils.hasText(model) && StringUtils.hasText(origin)){
            watches = getWatchRepository().findWatchesByModelLikeAndOriginLike(STR."%\{model}%",STR."%\{origin}%", pageRequest);

            return new PageImpl<>(watches.stream().map(getMapper()::watchToWatchDto).toList()).getContent();
        } else if (StringUtils.hasText(model)) {
            watches = getWatchRepository().findWatchesByModelLike(STR."%\{model}%", pageRequest);

            return new PageImpl<>(watches.stream().map(getMapper()::watchToWatchDto).toList()).getContent();
        } else if (StringUtils.hasText(origin)) {
            watches = getWatchRepository().findWatchesByOriginLike(STR."%\{origin}%", pageRequest);

            return new PageImpl<>(watches.stream().map(getMapper()::watchToWatchDto).toList()).getContent();
        } else {
            watches = getWatchRepository().findAll(pageRequest);

            return new PageImpl<>(watches.stream().map(getMapper()::watchToWatchDto).toList()).getContent();
        }
    }

    @Override
    @Transactional
    public WatchDto addWatch(WatchCreationDto watchCreationDto) {

        Watch createdWatch = getMapper().watchCreationDtoToWatch(watchCreationDto);

        Set<Category> categorySet = createdWatch.getCategories();
        Set<Category> validCategorySet = new HashSet<>();

        for (Category c : categorySet){
            Optional<Category> currCategory = getCategoryRepository().findById(c.getId());

            currCategory.ifPresent(validCategorySet::add);
        }

        createdWatch.setCategories(validCategorySet);

        createdWatch = getWatchRepository().save(createdWatch);

        return getMapper().watchToWatchDto(createdWatch);
    }

    @Override
    @Transactional
    public WatchDto updateById(UUID id, WatchCreationDto watch) {
        Optional<Watch> optionalExistingWatch = getWatchRepository().findById(id);

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

        existingWatch = getWatchRepository().save(existingWatch);

        return getMapper().watchToWatchDto(existingWatch);
    }

    @Override
    public void deleteById(UUID id) {
        getWatchRepository().deleteById(id);
    }

    @Override
    public CategoryDto addCategory(CategoryCreationDto dto) {
        Category createdCategory = getMapper().categoryCreationDtoToCategory(dto);

        createdCategory = getCategoryRepository().save(createdCategory);

        return getMapper().categoryToCategoryDto(createdCategory);
    }

    @Override
    public List<CategoryDto> getAllWatchCategories() {
        return getCategoryRepository().findAll().stream().map(getMapper()::categoryToCategoryDto).toList();
    }
}

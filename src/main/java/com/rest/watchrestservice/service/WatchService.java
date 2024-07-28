package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.CategoryCreationDto;
import com.rest.watchrestservice.dto.CategoryDto;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface WatchService {
    WatchDto getWatchById(UUID id);

    List<WatchDto> listWatches(String model, String origin, Integer pageSize, Integer pageNumber);

    WatchDto addWatch(WatchCreationDto watch);

    WatchDto updateById(UUID id, WatchCreationDto watch);

    void deleteById(UUID id);

    List<CategoryDto> getAllWatchCategories();

    CategoryDto addCategory(CategoryCreationDto dto);

}

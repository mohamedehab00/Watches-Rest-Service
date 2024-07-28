package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.dto.CategoryCreationDto;
import com.rest.watchrestservice.dto.CategoryDto;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.model.Category;
import com.rest.watchrestservice.service.WatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WatchController {
    private final WatchService watchService;
    public static final String WATCH_PATH = "/api/v1/watch";
    public static final String WATCH_PATH_ID = WATCH_PATH + "/{id}";

    public static final String WATCH_CATEGORIES_PATH = WATCH_PATH + "/categories";

    @GetMapping(WATCH_PATH)
    List<WatchDto> getAllWatches(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        log.debug("Retrieve All Available Watches");

        return this.watchService.listWatches(model, origin, size, page);
    }

    @GetMapping(WATCH_PATH_ID)
    WatchDto getWatchById(@PathVariable UUID id){
        log.debug(STR."Retrieve Watch With Id: \{id}");
        return this.watchService.getWatchById(id);
    }

    @PostMapping(WATCH_PATH)
    ResponseEntity<WatchDto> addWatch(@RequestBody WatchCreationDto watchCreationDto){
        log.debug(STR."Adding New Watch : \{watchCreationDto}");

        WatchDto createdWatch = this.watchService.addWatch(watchCreationDto);

        log.debug(STR."Watch Created with Id: \{createdWatch.getId()}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/watch/\{createdWatch.getId()}");

        return new ResponseEntity<>(createdWatch, headers, HttpStatus.CREATED);
    }

    @PutMapping(WATCH_PATH_ID)
    ResponseEntity<WatchDto> updateWatchById(@PathVariable UUID id, @RequestBody WatchCreationDto watchCreationDto){
        log.debug(STR."Update Watch with Id: \{id}");

        WatchDto updatedWatch = this.watchService.updateById(id,watchCreationDto);

        log.debug(STR."Watch Updated : \{updatedWatch}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/watch/\{updatedWatch.getId()}");

        return new ResponseEntity<>(updatedWatch, headers, HttpStatus.OK);
    }

    @DeleteMapping(WATCH_PATH_ID)
    ResponseEntity<WatchDto> deleteWatchById(@PathVariable UUID id){
        log.debug(STR."Delete Watch with Id: \{id}");

        this.watchService.deleteById(id);

        log.debug(STR."Watch Deleted with Id: \{id}");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(WATCH_CATEGORIES_PATH)
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreationDto dto){
        CategoryDto category = watchService.addCategory(dto);

        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }

    @GetMapping(WATCH_CATEGORIES_PATH)
    List<CategoryDto> getAllCategories(){
        return watchService.getAllWatchCategories();
    }
}
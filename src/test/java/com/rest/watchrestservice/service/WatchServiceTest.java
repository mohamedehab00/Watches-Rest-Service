package com.rest.watchrestservice.service;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.exceptions.ElementNotFoundException;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.repository.CategoryRepository;
import com.rest.watchrestservice.repository.WatchRepository;
import com.rest.watchrestservice.serviceImpl.WatchServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class WatchServiceTest {
    @Mock
    private WatchRepository watchRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private WatchServiceImpl watchService;

    @Test
    void getWatchById_Success() {
        UUID uuid = UUID.randomUUID();

        Watch watch = Mockito.mock(Watch.class);
        WatchDto mockWatchDto = Mockito.mock(WatchDto.class);

        Mockito.when(watchRepository.findById(uuid)).thenReturn(Optional.of(watch));
        Mockito.when(mapper.watchToWatchDto(watch)).thenReturn(mockWatchDto);

        WatchDto actualWatchDto = watchService.getWatchById(uuid);

        Assertions.assertThat(actualWatchDto).isNotNull();
        assertSame(mockWatchDto, actualWatchDto);
    }

    @Test
    void getWatchById_NotFound() {
        UUID uuid = UUID.randomUUID();

        Mockito.when(watchRepository.findById(uuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> watchService.getWatchById(uuid)).isInstanceOfAny(ElementNotFoundException.class);
    }

    @Test
    void listWatches_Success() {
        Page<Watch> watchesPage = Mockito.mock(Page.class);

        Mockito.when(watchRepository.findAll(Mockito.any(Pageable.class))).thenReturn(watchesPage);

        List<WatchDto> actualWatchDtos = watchService.getAllWatches(null,null,null,null);

        Assertions.assertThat(actualWatchDtos).isNotNull();
    }

    @Test
    void addWatch_Success() {
        WatchCreationDto mockWatchCreationDto = Mockito.mock(WatchCreationDto.class);
        Watch mockWatch = Mockito.mock(Watch.class);
        WatchDto mockWatchDto = Mockito.mock(WatchDto.class);

        Mockito.when(watchRepository.save(mockWatch)).thenReturn(mockWatch);
        Mockito.when(mapper.watchCreationDtoToWatch(mockWatchCreationDto)).thenReturn(mockWatch);
        Mockito.when(mapper.watchToWatchDto(mockWatch)).thenReturn(mockWatchDto);

        WatchDto dto = watchService.addWatch(mockWatchCreationDto);

        Assertions.assertThat(dto).isNotNull();
        assertSame(mockWatchDto, dto);
    }

    @Test
    void updateWatchById_Success() {
        Watch mockWatch = Mockito.mock(Watch.class);
        Watch mockWatchAfterSave = Mockito.mock(Watch.class);
        WatchDto mockWatchDto = Mockito.mock(WatchDto.class);
        WatchCreationDto mockWatchCreationDto = Mockito.mock(WatchCreationDto.class);

        UUID uuid = UUID.randomUUID();

        Mockito.when(watchRepository.findById(uuid)).thenReturn(Optional.of(mockWatch));
        Mockito.when(watchRepository.save(mockWatch)).thenReturn(mockWatchAfterSave);
        Mockito.when(mapper.watchToWatchDto(mockWatchAfterSave)).thenReturn(mockWatchDto);

        WatchDto dto = watchService.updateById(uuid,mockWatchCreationDto);

        Assertions.assertThat(dto).isNotNull();
        assertSame(mockWatchDto, dto);
    }

    @Test
    void updateWatchById_Fail() {
        WatchCreationDto mockWatchCreationDto = Mockito.mock(WatchCreationDto.class);

        UUID uuid = UUID.randomUUID();

        Mockito.when(watchRepository.findById(uuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> watchService.updateById(uuid,mockWatchCreationDto)).isInstanceOfAny(ElementNotFoundException.class);
    }
}
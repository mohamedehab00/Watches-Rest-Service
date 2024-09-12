package com.rest.watchrestservice.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WatchControllerTest {


    /*@Test
    void getAllWatches_success(){
        Page<Watch> watches = Mockito.mock(Page.class);

        Mockito.when(watchRepository.findAll(Mockito.any(Pageable.class))).thenReturn(watches);

        List<WatchDto> watchDtos = watchService.listWatches(null,null,null,null);

        Assertions.assertThat(watchDtos).isNotNull();
    }

    @Test
    void findWatchById_success(){

    }

    @Test
    void createWatch_success(){
        Watch watch = Watch.builder()
                .id(UUID.randomUUID())
                .model("Casio")
                .price(1500.00)
                .serialNumber("Ser1")
                .origin("Spain")
                .quantityOnHand(12)
                .categories(new HashSet<>())
                .orderLines(new HashSet<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        WatchCreationDto creationDto = new WatchCreationDto();

        WatchDto expectedWatchDto = new WatchDto();


        BeanUtils.copyProperties(watch, expectedWatchDto);
        BeanUtils.copyProperties(watch, creationDto);

        Mockito.when(watchRepository.save(Mockito.any(Watch.class))).thenReturn(watch);

        WatchDto actualWatchDto = watchService.addWatch(creationDto);

        Assertions.assertThat(actualWatchDto).isNotNull();
        Assertions.assertThat(actualWatchDto.getId()).isEqualByComparingTo(expectedWatchDto.getId());
    }*/
}
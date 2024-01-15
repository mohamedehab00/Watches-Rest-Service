package com.rest.watchrestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.service.CustomerService;
import com.rest.watchrestservice.service.WatchService;
import com.rest.watchrestservice.serviceImpl.CustomerServiceImpl;
import com.rest.watchrestservice.serviceImpl.WatchServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    WatchService watchService;

    WatchServiceImpl watchServiceImpl = new WatchServiceImpl();

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Test
    void testGetWatchById() throws Exception {
        Watch testWatch = watchServiceImpl.listWatches().get(0);

        given(watchService.getWatchById(testWatch.getId())).willReturn(testWatch);

        mockMvc.perform(get(HomeController.WATCH_PATH_ID,testWatch.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testWatch.getId().toString())))
                .andExpect(jsonPath("$.model", is(testWatch.getModel())))
                .andExpect(jsonPath("$.price", is(testWatch.getPrice())))
                .andExpect(jsonPath("$.origin", is(testWatch.getOrigin())))
                .andExpect(jsonPath("$.serialNumber", is(testWatch.getSerialNumber())))
                .andExpect(jsonPath("$.addedAt", is(testWatch.getAddedAt().toString())));
    }

    @Test
    void testListWatches() throws Exception {
        List<Watch> testListWatches = watchServiceImpl.listWatches();

        given(watchService.listWatches()).willReturn(testListWatches);

        mockMvc.perform(get(HomeController.WATCH_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(testListWatches.size())));
    }

    @Test
    void testCreateWatch() throws Exception {
        Watch testWatch = Watch.builder()
                .id(UUID.randomUUID())
                .model("Rolex")
                .serialNumber("1321564")
                .price(15000.0)
                .addedAt(LocalDateTime.now())
                .build();

        given(watchService.addWatch(testWatch)).willReturn(testWatch);

        mockMvc.perform(post(HomeController.WATCH_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testWatch)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andExpect(jsonPath("$.id",is(testWatch.getId().toString())))
                .andExpect(jsonPath("$.model",is(testWatch.getModel())))
                .andExpect(jsonPath("$.serialNumber",is(testWatch.getSerialNumber())))
                .andExpect(jsonPath("$.origin",is(testWatch.getOrigin())))
                .andExpect(jsonPath("$.price",is(testWatch.getPrice())))
                .andExpect(jsonPath("$.addedAt",is(testWatch.getAddedAt().toString())));
    }

    @Test
    void testUpdateWatch() throws Exception {
        Watch testWatch = watchServiceImpl.listWatches().get(0);

        given(watchService.updateById(testWatch.getId(),testWatch)).willReturn(testWatch);

        mockMvc.perform(put(HomeController.WATCH_PATH_ID,testWatch.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testWatch)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().exists("location"))
                .andExpect(jsonPath("$.id",is(testWatch.getId().toString())))
                .andExpect(jsonPath("$.model",is(testWatch.getModel())))
                .andExpect(jsonPath("$.serialNumber",is(testWatch.getSerialNumber())))
                .andExpect(jsonPath("$.origin",is(testWatch.getOrigin())))
                .andExpect(jsonPath("$.price",is(testWatch.getPrice())))
                .andExpect(jsonPath("$.addedAt",is(testWatch.getAddedAt().toString())));

                verify(watchService).updateById(any(UUID.class),any(Watch.class));
    }

    @Test
    void testDeleteWatch() throws Exception {
        Watch testWatch = watchServiceImpl.listWatches().get(0);

        mockMvc.perform(delete(HomeController.WATCH_PATH_ID,testWatch.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(watchService).deleteById(uuidArgumentCaptor.capture());

        assertThat(testWatch.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer testCustomer = customerServiceImpl.listCustomers().get(0);

        given(customerService.getCustomerById(testCustomer.getId())).willReturn(testCustomer);

        mockMvc.perform(get(HomeController.CUSTOMER_PATH_ID,testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.name",is(testCustomer.getName())))
                .andExpect(jsonPath("$.version",is(testCustomer.getVersion())))
                .andExpect(jsonPath("$.addedAt",is(testCustomer.getAddedAt().toString())));
    }

    @Test
    void testListCustomers() throws Exception {
        List<Customer> testListCustomers = customerServiceImpl.listCustomers();

        given(customerService.listCustomers()).willReturn(testListCustomers);

        mockMvc.perform(get(HomeController.CUSTOMER_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(testListCustomers.size())));
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer testCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .name("Mohamed Ali")
                .version(5)
                .addedAt(LocalDateTime.now())
                .build();

        given(customerService.addCustomer(testCustomer)).willReturn(testCustomer);

        mockMvc.perform(post(HomeController.CUSTOMER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCustomer)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.name",is(testCustomer.getName())))
                .andExpect(jsonPath("$.version",is(testCustomer.getVersion())))
                .andExpect(jsonPath("$.addedAt",is(testCustomer.getAddedAt().toString())));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer testCustomer = customerServiceImpl.listCustomers().get(0);

        given(customerService.updateById(testCustomer.getId(),testCustomer)).willReturn(testCustomer);

        mockMvc.perform(put(HomeController.CUSTOMER_PATH_ID,testCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCustomer)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.name",is(testCustomer.getName())))
                .andExpect(jsonPath("$.version",is(testCustomer.getVersion())))
                .andExpect(jsonPath("$.addedAt",is(testCustomer.getAddedAt().toString())));

        verify(customerService).updateById(any(UUID.class),any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Customer testCustomer = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(delete(HomeController.CUSTOMER_PATH_ID,testCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteById(uuidArgumentCaptor.capture());

        assertThat(testCustomer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
}

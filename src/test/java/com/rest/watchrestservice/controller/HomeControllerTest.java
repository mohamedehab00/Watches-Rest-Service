package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.service.CustomerService;
import com.rest.watchrestservice.service.WatchService;
import com.rest.watchrestservice.serviceImpl.CustomerServiceImpl;
import com.rest.watchrestservice.serviceImpl.WatchServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WatchService watchService;

    WatchServiceImpl watchServiceImpl = new WatchServiceImpl();

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @Test
    void getWatchById() throws Exception {
        Watch testWatch = watchServiceImpl.listWatches().get(0);

        given((watchService.getWatchById(testWatch.getId()))).willReturn(testWatch);

        mockMvc.perform(get("/api/v1/watch/"+ testWatch.getId())
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
    void listWatches() throws Exception {
        List<Watch> testListWatches = watchServiceImpl.listWatches();

        given(watchService.listWatches()).willReturn(testListWatches);

        mockMvc.perform(get("/api/v1/watch")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(testListWatches.size())));
    }

    @Test
    void getCustomerById() throws Exception {
        Customer testCustomer = customerServiceImpl.listCustomers().get(0);

        given(customerService.getCustomerById(testCustomer.getId())).willReturn(testCustomer);

        mockMvc.perform(get("/api/v1/customer/"+testCustomer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.name",is(testCustomer.getName())))
                .andExpect(jsonPath("$.version",is(testCustomer.getVersion())))
                .andExpect(jsonPath("$.addedAt",is(testCustomer.getAddedAt().toString())));
    }

    @Test
    void listCustomers() throws Exception {
        List<Customer> testListCustomers = customerServiceImpl.listCustomers();

        given(customerService.listCustomers()).willReturn(testListCustomers);

        mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(testListCustomers.size())));
    }
}

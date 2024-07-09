package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class WatchGraphQlController {
    @Autowired
    private WatchRepository repository;

    @QueryMapping
    public List<Watch> getAllWatches() {
        return repository.findAll();
    }

    @QueryMapping
    public Watch getWatchById(@Argument UUID id){
        return repository.findById(id).orElseThrow();
    }
}

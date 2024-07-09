package com.rest.watchrestservice.repository;

import com.rest.watchrestservice.model.WatchOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatchOrderLineRepository extends JpaRepository<WatchOrderLine, UUID> {
}
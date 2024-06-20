package com.rest.watchrestservice.repository;

import com.rest.watchrestservice.model.WatchOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WatchOrderRepository extends JpaRepository<WatchOrder, UUID> {
}

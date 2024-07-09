package com.rest.watchrestservice.repository;

import com.rest.watchrestservice.model.WatchOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WatchOrderRepository extends JpaRepository<WatchOrder, UUID> {
    @Query(value = "SELECT UUID()", nativeQuery = true)
    UUID getNextIdValue();
}

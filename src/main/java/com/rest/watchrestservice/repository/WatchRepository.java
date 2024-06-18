package com.rest.watchrestservice.repository;

import com.rest.watchrestservice.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository<Watch,String> {
}
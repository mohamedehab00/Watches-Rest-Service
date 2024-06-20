package com.rest.watchrestservice.repository;

import com.rest.watchrestservice.model.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatchRepository extends JpaRepository<Watch, UUID> {
    Page<Watch> findWatchesByOriginLike(String origin, Pageable pageable);
    Page<Watch> findWatchesByModelLike(String model, Pageable pageable);

    Page<Watch> findWatchesByModelLikeAndOriginLike(String model, String origin, Pageable pageable);
}
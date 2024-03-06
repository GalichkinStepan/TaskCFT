package com.cft.FirstTask.repository;

import com.cft.FirstTask.model.IntInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IntIntervalRepository extends JpaRepository<IntInterval, Long> {
    @Query(value = "SELECT ii.* FROM intintervals ii WHERE start1 = (SELECT min(ii1.start1) FROM intintervals ii1 LIMIT 1) AND end1 = (SELECT min(ii2.end1) FROM intintervals ii2 LIMIT 1) LIMIT 1", nativeQuery = true)
    IntInterval findMinInterval();
}

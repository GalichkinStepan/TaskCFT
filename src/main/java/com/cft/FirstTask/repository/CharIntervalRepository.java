package com.cft.FirstTask.repository;

import com.cft.FirstTask.model.CharInterval;
import com.cft.FirstTask.model.IntInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharIntervalRepository extends JpaRepository<CharInterval, Long> {
    @Query(value = "SELECT ci.* FROM charintervals ci WHERE start1 = (SELECT min(ci1.start1) FROM charintervals ci1 LIMIT 1) AND end1 = (SELECT min(ci2.end1) FROM charintervals ci2 LIMIT 1) LIMIT 1", nativeQuery = true)
    CharInterval findMinInterval();
}

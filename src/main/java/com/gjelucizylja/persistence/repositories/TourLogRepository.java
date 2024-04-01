package com.gjelucizylja.persistence.repositories;

import com.gjelucizylja.persistence.entities.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourLogRepository extends JpaRepository<TourLog, Long> {
    List<TourLog> findByTourId(Long tourId);
}

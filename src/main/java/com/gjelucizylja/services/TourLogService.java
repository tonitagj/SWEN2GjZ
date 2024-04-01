package com.gjelucizylja.services;

import com.gjelucizylja.services.dtos.TourLogDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourLogService {
    boolean createTourLog(TourLogDto tourLogDto);
    TourLogDto updateTourLog(Long id, TourLogDto tourLogDto);
    void deleteTourLog(Long id);
    TourLogDto getTourLogById(Long id);
    List<TourLogDto> getAllTourLogs();
    List<TourLogDto> getTourLogsByTourId(Long tourId);
}

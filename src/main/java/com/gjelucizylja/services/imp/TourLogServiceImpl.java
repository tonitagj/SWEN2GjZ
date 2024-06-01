package com.gjelucizylja.services.imp;

import com.gjelucizylja.persistence.entities.Tour;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.persistence.repositories.TourLogRepository;
import com.gjelucizylja.persistence.repositories.TourRepository;
import com.gjelucizylja.services.TourLogService;
import com.gjelucizylja.services.dtos.TourLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TourLogServiceImpl implements TourLogService {

    @Autowired
    private TourLogRepository tourLogRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public boolean createTourLog(TourLogDto tourLogDto) {
        log.info("Creating new tour log: {}", tourLogDto);
        Optional<Tour> optionalTour = tourRepository.findById(tourLogDto.getTourId());
        if (optionalTour.isEmpty()) {
            log.error("Tour not found with id: {}", tourLogDto.getTourId());
            throw new IllegalArgumentException("Tour not found");
        }

        Tour tour = optionalTour.get();
        TourLog tourLog = new TourLog();
        tourLog.setTour(tour);
        tourLog.setDate(tourLogDto.getDate());
        tourLog.setComment(tourLogDto.getComment());
        tourLog.setDifficulty(tourLogDto.getDifficulty());
        tourLog.setTotalDistance(tourLogDto.getTotalDistance());
        tourLog.setTotalTime(tourLogDto.getTotalTime());
        tourLog.setRating(tourLogDto.getRating());

        TourLog savedTourLog = tourLogRepository.save(tourLog);
        log.info("Tour log saved successfully with id: {}", savedTourLog.getId());
        return true;
    }
    @Override
    public TourLogDto updateTourLog(Long id, TourLogDto tourLogDto) {
        log.info("Updating tour log with id: {}", id);
        Optional<TourLog> optionalTourLog = tourLogRepository.findById(id);
        if (optionalTourLog.isEmpty()) {
            log.error("Tour log not found with id: {}", id);
            throw new IllegalArgumentException("Tour log not found");
        }

        TourLog tourLog = optionalTourLog.get();
        tourLog.setDate((tourLogDto.getDate()));
        tourLog.setComment(tourLogDto.getComment());
        tourLog.setDifficulty(tourLogDto.getDifficulty());
        tourLog.setTotalDistance(tourLogDto.getTotalDistance());
        tourLog.setTotalTime(tourLogDto.getTotalTime());
        tourLog.setRating(tourLogDto.getRating());

        TourLog updatedTourLog = tourLogRepository.save(tourLog);
        log.info("Tour log updated successfully with id: {}", updatedTourLog.getId());
        return mapToDto(updatedTourLog);
    }

    @Override
    public void deleteTourLog(Long id) {
        // Delete the tour log by its ID
        log.info("Deleting tour log with id: {}", id);
        tourLogRepository.deleteById(id);
        log.info("Tour log deleted successfully with id: {}", id);
    }

    @Override
    public List<TourLogDto> getAllTourLogs() {
        log.info("Fetching all tour logs");
        // Retrieve all tour logs from the repository
        List<TourLog> tourLogs = tourLogRepository.findAll();
        log.info("Fetched {} tour logs", tourLogs.size());
        return tourLogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TourLogDto getTourLogById(Long id) {
        // Retrieve a tour log by its ID
        log.info("Fetching tour log by id: {}", id);
        Optional<TourLog> optionalTourLog = tourLogRepository.findById(id);
        if (optionalTourLog.isEmpty()) {
            log.error("Tour log not found with id: {}", id);
            throw new IllegalArgumentException("Tour log not found");
        }
        return mapToDto(optionalTourLog.get());
    }

    @Override
    public List<TourLogDto> getTourLogsByTourId(Long tourId) {
        log.info("Fetching tour logs by tour id: {}", tourId);
        List<TourLog> tourLogs = tourLogRepository.findByTourId(tourId);
        log.info("Fetched {} tour logs for tour id: {}", tourLogs.size(), tourId);
        return tourLogs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TourLogDto mapToDto(TourLog tourLog) {
        TourLogDto dto = new TourLogDto();
        dto.setId(tourLog.getId());
        dto.setTourId(tourLog.getTour().getId());
        dto.setDate(tourLog.getDate());
        dto.setComment(tourLog.getComment());
        dto.setDifficulty(tourLog.getDifficulty());
        dto.setTotalDistance(tourLog.getTotalDistance());
        dto.setTotalTime(tourLog.getTotalTime());
        dto.setRating(tourLog.getRating());
        return dto;
    }

}

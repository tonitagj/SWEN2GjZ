package com.gjelucizylja.services.imp;

import com.gjelucizylja.persistence.entities.Tour;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.persistence.repositories.TourLogRepository;
import com.gjelucizylja.persistence.repositories.TourRepository;
import com.gjelucizylja.services.TourLogService;
import com.gjelucizylja.services.dtos.TourLogDto;
import com.gjelucizylja.services.mapper.TourLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TourLogServiceImpl implements TourLogService {
    @Autowired
    private TourLogRepository tourLogRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public TourLogDto createTourLog(TourLogDto tourLogDto) {
        Optional<Tour> optionalTour = tourRepository.findById(tourLogDto.getTourId());
        if (optionalTour.isEmpty()) {
            throw new IllegalArgumentException("Tour not found");
        }

        Tour tour = optionalTour.get();
        TourLog tourLog = new TourLog();
        tourLog.setTour(tour);
        tourLog.setDateTime(tourLogDto.getDateTime());
        tourLog.setComment(tourLogDto.getComment());
        tourLog.setDifficulty(tourLogDto.getDifficulty());
        tourLog.setTotalDistance(tourLogDto.getTotalDistance());
        tourLog.setTotalTime(tourLogDto.getTotalTime());
        tourLog.setRating(tourLogDto.getRating());

        TourLog savedTourLog = tourLogRepository.save(tourLog);
        return mapToDto(savedTourLog);
    }
    @Override
    public TourLogDto updateTourLog(Long id, TourLogDto tourLogDto) {
        Optional<TourLog> optionalTourLog = tourLogRepository.findById(id);
        if (optionalTourLog.isEmpty()) {
            throw new IllegalArgumentException("Tour log not found");
        }

        TourLog tourLog = optionalTourLog.get();
        tourLog.setDateTime(tourLogDto.getDateTime());
        tourLog.setComment(tourLogDto.getComment());
        tourLog.setDifficulty(tourLogDto.getDifficulty());
        tourLog.setTotalDistance(tourLogDto.getTotalDistance());
        tourLog.setTotalTime(tourLogDto.getTotalTime());
        tourLog.setRating(tourLogDto.getRating());

        TourLog updatedTourLog = tourLogRepository.save(tourLog);
        return mapToDto(updatedTourLog);
    }

    @Override
    public void deleteTourLog(Long id) {
        // Delete the tour log by its ID
        tourLogRepository.deleteById(id);
    }

    @Override
    public List<TourLogDto> getAllTourLogs() {
        // Retrieve all tour logs from the repository
        List<TourLog> tourLogs = tourLogRepository.findAll();
        return tourLogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TourLogDto getTourLogById(Long id) {
        // Retrieve a tour log by its ID
        Optional<TourLog> optionalTourLog = tourLogRepository.findById(id);
        if (optionalTourLog.isEmpty()) {
            throw new IllegalArgumentException("Tour log not found");
        }
        return mapToDto(optionalTourLog.get());
    }

    @Override
    public List<TourLogDto> getTourLogsByTourId(Long tourId) {
        List<TourLog> tourLogs = tourLogRepository.findByTourId(tourId);
        return tourLogs.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TourLogDto mapToDto(TourLog tourLog) {
        TourLogDto dto = new TourLogDto();
        dto.setTourId(tourLog.getTour().getId());
        dto.setDateTime(tourLog.getDateTime());
        dto.setComment(tourLog.getComment());
        dto.setDifficulty(tourLog.getDifficulty());
        dto.setTotalDistance(tourLog.getTotalDistance());
        dto.setTotalTime(tourLog.getTotalTime());
        dto.setRating(tourLog.getRating());
        return dto;
    }
}

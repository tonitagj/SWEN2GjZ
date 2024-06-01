package com.gjelucizylja.services.mapper;

import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.services.dtos.TourLogDto;
import org.springframework.stereotype.Component;

@Component
public class TourLogMapper extends AbstractMapper<TourLog, TourLogDto>{
    @Override
    public TourLogDto toDto(TourLog tourLog) {
        return TourLogDto.builder()
                .id(tourLog.getId())
                .tourId(tourLog.getTour().getId())
                .date(tourLog.getDate())
                .comment(tourLog.getComment())
                .difficulty(tourLog.getDifficulty())
                .totalDistance(tourLog.getTotalDistance())
                .totalTime(tourLog.getTotalTime())
                .rating(tourLog.getRating())
                .build();
    }
    @Override
    public TourLog toEntity(TourLogDto tourLogDto) {
        TourLog tourLog = new TourLog();
        tourLog.setId(tourLogDto.getId());
        tourLog.setTour(tourLog.getTour());
        tourLog.setRating(tourLog.getRating());
        tourLog.setComment(tourLog.getComment());
        tourLog.setDifficulty(tourLog.getDifficulty());
        tourLog.setDate(tourLog.getDate());
        tourLog.setTotalDistance(tourLog.getTotalDistance());
        tourLog.setTotalTime(tourLog.getTotalTime());
        return tourLog;
    }

    @Override
    public TourLogDto mapToDto(TourLog source) {
        return null;
    }
}

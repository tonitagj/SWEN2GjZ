package com.gjelucizylja.services.mapper;

import com.gjelucizylja.persistence.entities.Tour;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.services.dtos.TourDto;
import com.gjelucizylja.services.dtos.TourLogDto;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends AbstractMapper<Tour, TourDto>{
    @Override
    public TourDto mapToDto(Tour source) {
        return TourDto.builder()
                .id(source.getId())
                .name(source.getName())
                .tourDescription(source.getTourDescription())
                .fromLocation(source.getFromLocation())
                .toLocation(source.getToLocation())
                .transportType(source.getTransportType())
                .tourDistance(source.getTourDistance())
                .estimatedTime(source.getEstimatedTime())
                .routeInformationImageUrl(source.getRouteInformationImageUrl())
                .build();
    }

    @Override
    public TourLogDto toDto(TourLog tourLog) {
        return null;
    }

    @Override
    public TourLog toEntity(TourLogDto tourLogDto) {
        return null;
    }
}

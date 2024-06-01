package com.gjelucizylja.services.imp;



import com.gjelucizylja.persistence.entities.Tour;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.persistence.repositories.TourLogRepository;
import com.gjelucizylja.persistence.repositories.TourRepository;
import com.gjelucizylja.services.TourService;
import com.gjelucizylja.services.dtos.TourDto;
import com.gjelucizylja.services.mapper.TourMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourMapper tourMapper;
    @Autowired
    private TourLogRepository tourLogRepository;

    @Override
    public boolean saveNewTour(TourDto tourDto) {
        log.info("Saving new tour: {}", tourDto);
        Tour tour = Tour.builder()
                .name(tourDto.getName())
                .tourDescription(tourDto.getTourDescription())
                .fromLocation(tourDto.getFromLocation())
                .toLocation(tourDto.getToLocation())
                .transportType(tourDto.getTransportType())
                .routeInformationImageUrl(tourDto.getRouteInformationImageUrl())
                .tourDistance(tourDto.getTourDistance())
                .estimatedTime(tourDto.getEstimatedTime())
                .build();

        // Save the tour entity
        tourRepository.save(tour);
        log.info("Tour saved successfully with id: {}", tour.getId());
        return true;
    }



    @Override
    public List<TourDto> getAllTours() {
        log.info("Fetching all tours");
        List<Tour> tours = tourRepository.findAll();
        log.info("Fetched {} tours", tours.size());
        return tourMapper.mapToDto(tours);
    }



    @Override
    public TourDto getTourById(Long id) {
        log.info("Fetching tour by id: {}", id);
        Tour tour = tourRepository.findById(id).orElse(null);
        log.warn("Tour not found with id: {}", id);
        return tour != null ? tourMapper.mapToDto(tour) : null;
    }

    @Override
    public TourDto updateTour(Long id, TourDto tourDto) {
        log.info("Updating tour with id: {}", id);
        // Check if the tour with the given name exists
        Tour existingTour = tourRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tour not found with ID: " + id));

        // Update the properties of the existing tour entity with the values from the DTO
        existingTour.setName(tourDto.getName());
        existingTour.setTourDescription(tourDto.getTourDescription());
        existingTour.setFromLocation(tourDto.getFromLocation());
        existingTour.setToLocation(tourDto.getToLocation());
        existingTour.setTransportType(tourDto.getTransportType());
        existingTour.setEstimatedTime(tourDto.getEstimatedTime());
        existingTour.setRouteInformationImageUrl(tourDto.getRouteInformationImageUrl());
        existingTour.setTourDistance(tourDto.getTourDistance());


        // Save the updated tour entity
        Tour updatedTour = tourRepository.save(existingTour);
        log.info("Tour updated successfully with id: {}", updatedTour.getId());
        // Map the updated tour entity to a DTO and return it
        return tourMapper.mapToDto(updatedTour);
    }

    @Override
    public void deleteTour(Long id) {
        // Find the tour by ID
        log.info("Deleting tour with id: {}", id);
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();

            // Delete tour logs associated with the tour
            List<TourLog> tourLogs = tour.getTourLogs();
            for (TourLog tourLog : tourLogs) {
                log.info("Deleting tour log with id: {}", tourLog.getId());
                tourLogRepository.delete(tourLog);
            }

            // Delete the tour
            tourRepository.deleteById(id);
            log.info("Tour deleted successfully with id: {}", id);
        } else {
            log.error("Tour not found with id: {}", id);
            throw new IllegalArgumentException("Tour not found");
        }
    }
}

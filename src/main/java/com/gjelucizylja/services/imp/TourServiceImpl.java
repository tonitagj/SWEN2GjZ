package com.gjelucizylja.services.imp;



import com.gjelucizylja.persistence.entities.Tour;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.persistence.repositories.TourLogRepository;
import com.gjelucizylja.persistence.repositories.TourRepository;
import com.gjelucizylja.services.TourService;
import com.gjelucizylja.services.dtos.TourDto;
import com.gjelucizylja.services.mapper.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourMapper tourMapper;
    @Autowired
    private TourLogRepository tourLogRepository;
    private static final String OPEN_ROUTE_SERVICE_API_URL = "https://www.openstreetmap.org/";
    private static final String API_KEY = "5b3ce3597851110001cf624830b5a27b3c72412995c2f2c97814a47e"; //My key

    @Override
    public TourDto saveNewTour(TourDto tourDto) {
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
        return tourDto;
    }



    @Override
    public List<TourDto> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        return tourMapper.mapToDto(tours);
    }

    @Override
    public TourDto getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElse(null);
        return tour != null ? tourMapper.mapToDto(tour) : null;
    }

    @Override
    public TourDto updateTour(String name, TourDto tourDto) {
        // Check if the tour with the given name exists
        Tour existingTour = tourRepository.findTourByNameIgnoreCase(name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tour not found with name: " + name));

        // Update the properties of the existing tour entity with the values from the DTO
        existingTour.setName(tourDto.getName());
        existingTour.setTourDescription(tourDto.getTourDescription());
        existingTour.setFromLocation(tourDto.getFromLocation());
        existingTour.setToLocation(tourDto.getToLocation());
        existingTour.setTransportType(tourDto.getTransportType());
        // Update other properties as needed

        // Save the updated tour entity
        Tour updatedTour = tourRepository.save(existingTour);

        // Map the updated tour entity to a DTO and return it
        return tourMapper.mapToDto(updatedTour);
    }

    @Override
    public void deleteTour(Long id) {
        // Find the tour by ID
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();

            // Delete tour logs associated with the tour
            List<TourLog> tourLogs = tour.getTourLogs();
            for (TourLog tourLog : tourLogs) {
                tourLogRepository.delete(tourLog);
            }

            // Delete the tour
            tourRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tour not found");
        }
    }
}

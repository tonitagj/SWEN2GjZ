package com.gjelucizylja.services;

import com.gjelucizylja.services.dtos.TourDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TourService {

    boolean saveNewTour(TourDto tourDto);
    List<TourDto> getAllTours();
    TourDto getTourById(Long id);
    TourDto updateTour(String name, TourDto tourDto);
    void deleteTour(Long id);
}
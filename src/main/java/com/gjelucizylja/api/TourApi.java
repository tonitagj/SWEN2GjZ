package com.gjelucizylja.api;

import com.gjelucizylja.services.TourService;
import com.gjelucizylja.services.dtos.TourDto;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour")
@Slf4j
public class TourApi {

    @Autowired
    private TourService tourService;

    @PostMapping
    public ResponseEntity saveNewTour(@RequestBody TourDto tourDto) {
        log.info("Saving new tour: {}", tourDto);
       if(tourService.saveNewTour(tourDto)){
           log.info("Tour saved successfully: {}", tourDto);
           return new ResponseEntity<>("OK", HttpStatus.CREATED);
       }else{
           log.error("Error occurred while saving tour: {}", tourDto);
           return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping
    public List<TourDto> getAllTours() {
        log.info("Fetching all tours");
        return tourService.getAllTours();
    }

    @GetMapping("/{id}")
    public TourDto getTourById(@PathVariable Long id) {
        log.info("Fetching tour by id: {}", id);
        return tourService.getTourById(id);
    }

    @PutMapping("/{id}")
    public void updateTour(@PathVariable Long id, @RequestBody TourDto tourDto) {
        log.info("Updating tour with id: {}", id);
        tourService.updateTour(id, tourDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        log.info("Deleting tour by id: {}", id);
        tourService.deleteTour(id);
    }
}

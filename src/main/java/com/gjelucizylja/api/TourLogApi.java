package com.gjelucizylja.api;

import com.gjelucizylja.services.TourLogService;
import com.gjelucizylja.services.dtos.TourLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour-logs")
@Slf4j
public class TourLogApi {
    @Autowired
    private TourLogService tourLogService;

    @PostMapping
    public ResponseEntity createTourLog(@RequestBody TourLogDto tourLogDto) {
        log.info("Creating new tour log: {}", tourLogDto);
        if(tourLogService.createTourLog(tourLogDto)){
            log.info("Tour log created successfully: {}", tourLogDto);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        }else {
            log.error("Error occurred while creating tour log: {}", tourLogDto);
            return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<TourLogDto> updateTourLog(@PathVariable Long id, @RequestBody TourLogDto tourLogDto) {
        log.info("Updating tour log with id: {}", id);
        TourLogDto updatedTourLog = tourLogService.updateTourLog(id, tourLogDto);
        log.info("Tour log updated successfully: {}", updatedTourLog);
        return new ResponseEntity<>(updatedTourLog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourLog(@PathVariable Long id) {
        log.info("Deleting tour log with id: {}", id);
        tourLogService.deleteTourLog(id);
        log.info("Tour log deleted successfully with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TourLogDto>> getAllTourLogs() {
        log.info("Fetching all tour logs");
        List<TourLogDto> tourLogs = tourLogService.getAllTourLogs();
        log.info("Fetched {} tour logs", tourLogs.size());
        return ResponseEntity.ok(tourLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourLogDto> getTourLogById(@PathVariable Long id) {
        log.info("Fetching tour log by id: {}", id);
        TourLogDto tourLog = tourLogService.getTourLogById(id);
        log.info("Fetched tour log: {}", tourLog);
        return ResponseEntity.ok(tourLog);
    }

    @GetMapping("/by-tour/{tourId}")
    public ResponseEntity<List<TourLogDto>> getTourLogsByTourId(@PathVariable Long tourId) {
        log.info("Fetching tour logs by tour id: {}", tourId);
        List<TourLogDto> tourLogs = tourLogService.getTourLogsByTourId(tourId);
        log.info("Fetched {} tour logs for tour id: {}", tourLogs.size(), tourId);
        return ResponseEntity.ok(tourLogs);
    }


}

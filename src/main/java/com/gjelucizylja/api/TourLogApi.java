package com.gjelucizylja.api;

import com.gjelucizylja.services.TourLogService;
import com.gjelucizylja.services.dtos.TourLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour-logs")
public class TourLogApi {
    @Autowired
    private TourLogService tourLogService;

    @PostMapping
    public ResponseEntity<TourLogDto> createTourLog(@RequestBody TourLogDto tourLogDto) {
        TourLogDto createdTourLog = tourLogService.createTourLog(tourLogDto);
        return new ResponseEntity<>(createdTourLog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourLogDto> updateTourLog(@PathVariable Long id, @RequestBody TourLogDto tourLogDto) {
        TourLogDto updatedTourLog = tourLogService.updateTourLog(id, tourLogDto);
        return new ResponseEntity<>(updatedTourLog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourLog(@PathVariable Long id) {
        tourLogService.deleteTourLog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TourLogDto>> getAllTourLogs() {
        List<TourLogDto> tourLogs = tourLogService.getAllTourLogs();
        return ResponseEntity.ok(tourLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourLogDto> getTourLogById(@PathVariable Long id) {
        TourLogDto tourLog = tourLogService.getTourLogById(id);
        return ResponseEntity.ok(tourLog);
    }

    @GetMapping("/by-tour/{tourId}")
    public ResponseEntity<List<TourLogDto>> getTourLogsByTourId(@PathVariable Long tourId) {
        List<TourLogDto> tourLogs = tourLogService.getTourLogsByTourId(tourId);
        return ResponseEntity.ok(tourLogs);
    }


}

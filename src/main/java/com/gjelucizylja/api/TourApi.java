package com.gjelucizylja.api;

import com.gjelucizylja.services.TourService;
import com.gjelucizylja.services.dtos.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour")
public class TourApi {
    @Autowired
    private TourService tourService;

    @PostMapping
    public TourDto saveNewTour(@RequestBody TourDto tourDto) {
        return tourService.saveNewTour(tourDto);
    }

    @GetMapping
    public List<TourDto> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/{id}")
    public TourDto getTourById(@PathVariable Long id) {
        return tourService.getTourById(id);
    }

    @PutMapping("/{name}")
    public void updateTour(@PathVariable String name, @RequestBody TourDto tourDto) {
        tourService.updateTour(tourDto.getName(), tourDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
    }
}

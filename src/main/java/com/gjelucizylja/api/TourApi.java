package com.gjelucizylja.api;

import com.gjelucizylja.services.TourService;
import com.gjelucizylja.services.dtos.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour")
public class TourApi {
    @Autowired
    private TourService tourService;

    @PostMapping
    public ResponseEntity saveNewTour(@RequestBody TourDto tourDto) {
       if(tourService.saveNewTour(tourDto)){
           return new ResponseEntity<>("OK", HttpStatus.CREATED);
       }else{
           return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
       }
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

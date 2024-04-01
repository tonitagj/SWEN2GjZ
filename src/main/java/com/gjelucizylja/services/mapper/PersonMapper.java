package com.gjelucizylja.services.mapper;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.services.dtos.PersonDto;
import com.gjelucizylja.services.dtos.TourLogDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    @Override
    public PersonDto mapToDto(Person source) {
        return PersonDto.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .age(source.getAge())
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

package com.gjelucizylja.services.mapper;

public interface Mapper<S, T> {

    T mapToDto(S source);

}
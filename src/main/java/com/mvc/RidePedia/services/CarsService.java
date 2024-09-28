package com.mvc.RidePedia.services;

import com.mvc.RidePedia.dto.CarDto;
import com.mvc.RidePedia.dto.CarResponse;


public interface CarsService {
    CarDto createCar(CarDto carDto);
    CarResponse getAllCars(int pageNo, int pageSize );

    CarDto getCarById(int Id);

    CarDto updateCar(CarDto carDto, int id);


    void deleteCar(int id);
}

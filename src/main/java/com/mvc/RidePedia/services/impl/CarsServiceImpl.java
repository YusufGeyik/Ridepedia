package com.mvc.RidePedia.services.impl;

import com.mvc.RidePedia.dto.CarDto;
import com.mvc.RidePedia.dto.CarResponse;
import com.mvc.RidePedia.exceptions.CarNotFoundEx;
import com.mvc.RidePedia.models.Cars;
import com.mvc.RidePedia.repository.CarsRepository;
import com.mvc.RidePedia.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {


    private CarsRepository carsRepository;
    @Autowired
    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }


    @Override
    public CarDto createCar(CarDto carDto) {
        Cars cars =new Cars();
        cars.setName(carDto.getName());
        cars.setType(carDto.getType());

        Cars newCars = carsRepository.save(cars);


        CarDto carResponse=new CarDto();

        carResponse.setId(newCars.getId());
        carResponse.setName(carDto.getName());
        carResponse.setType(newCars.getType());
        return carResponse;

    }

    @Override
    public CarResponse getAllCars(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Cars> pageOfCars= carsRepository.findAll(pageable);
        List<Cars> listOfCars =pageOfCars.getContent();
        List<CarDto> content= listOfCars.stream().map(cars -> mapToDto(cars)).collect(Collectors.toList());
        CarResponse carResponse =new CarResponse();
        carResponse.setContent(content);
        carResponse.setPageNo(pageOfCars.getNumber());
        carResponse.setPageSize(pageOfCars.getSize());
        carResponse.setTotalElements(pageOfCars.getTotalElements());
        carResponse.setTotalPages(pageOfCars.getTotalPages());
        carResponse.setLast(pageOfCars.isLast());

        return carResponse;

    }

    @Override
    public CarDto getCarById(int Id) {
        Cars cars = carsRepository.findById(Id).orElseThrow(()->new CarNotFoundEx("Car not found"));


            return mapToDto(cars);

    }

    @Override
    public CarDto updateCar(CarDto carDtoref, int id) {

        Cars cars = carsRepository.findById(id).orElseThrow(()->new CarNotFoundEx("not updated"));

        cars.setName(carDtoref.getName());
        cars.setType(carDtoref.getType());


        Cars updatedCars = carsRepository.save(cars);


        return mapToDto(updatedCars);
    }



    @Override
    public void deleteCar(int id) {
        Cars cars = carsRepository.findById(id).orElseThrow(()-> new CarNotFoundEx("cars couldn't deleted"));
        carsRepository.deleteById(id);
    }

    private CarDto mapToDto(Cars cars)
    {
        CarDto carDto =new CarDto();
        carDto.setName(cars.getName());
        carDto.setId(cars.getId());
        carDto.setType(cars.getType());
        return carDto;
    }
    private Cars mapToEntity(CarDto carDto)
    {
        Cars cars =new Cars();
        cars.setName(carDto.getName());
        cars.setId(carDto.getId());
        cars.setType(carDto.getType());
        return cars;


    }



}

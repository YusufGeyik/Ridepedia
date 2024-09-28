package com.mvc.RidePedia.repository;

import com.mvc.RidePedia.models.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars,Integer> {

}

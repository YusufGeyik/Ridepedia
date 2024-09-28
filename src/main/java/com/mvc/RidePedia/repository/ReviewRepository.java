package com.mvc.RidePedia.repository;

import com.mvc.RidePedia.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Reviews,Integer> {

    List<Reviews> findByCarId(Integer carId); //review sınıfındaki car_Id kolonuna bakar
}

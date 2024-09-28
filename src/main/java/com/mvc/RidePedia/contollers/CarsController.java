package com.mvc.RidePedia.contollers;

import com.mvc.RidePedia.dto.CarDto;
import com.mvc.RidePedia.dto.CarResponse;
import com.mvc.RidePedia.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarsController {


    private CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }





    @GetMapping("/ridepedia")
    public ResponseEntity<CarResponse> getCars
            (@RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
             @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize)


    {

      return new ResponseEntity<>(carsService.getAllCars(pageNo,pageSize),HttpStatus.OK);

    }






    @GetMapping("/ridepedia/{id}")
        public ResponseEntity<CarDto> CarDetail(@PathVariable int id)
        {
            return new ResponseEntity<>(carsService.getCarById(id),HttpStatus.OK);
        }




        @PostMapping("/ridepedia/create")
        @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto)
        {
            return new ResponseEntity<>(carsService.createCar(carDto),HttpStatus.CREATED);

        }





        @PutMapping("/ridepedia/{id}/update")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto, @PathVariable int id)
        {


         return new ResponseEntity<>(carsService.updateCar(carDto,id),HttpStatus.OK);
        }






        @DeleteMapping("/ridepedia/{id}/delete")
        public ResponseEntity<String> deleteCar(@PathVariable int id)
        {
            carsService.deleteCar(id);

            return new ResponseEntity<>("Car Deleted",HttpStatus.OK);
        }
}

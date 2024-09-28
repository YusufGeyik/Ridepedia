package com.mvc.RidePedia.contollers;

import com.mvc.RidePedia.dto.ReviewDto;
import com.mvc.RidePedia.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService=reviewService;
    }

    
@PostMapping(path = "/ridepedia/{carId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "carId") int carId, @RequestBody ReviewDto reviewDto)
{
     return new ResponseEntity<>(reviewService.createReview(carId,reviewDto), HttpStatus.CREATED);

}

@GetMapping(path = "/ridepedia/{carId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable(value = "carId") int carId)
{

   return new ResponseEntity<>(reviewService.getReviewsByCarId(carId),HttpStatus.OK);



}

@GetMapping(path = "/ridepedia/{carId}/reviews/{reviewId}")
  public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value="reviewId")int reviewId,@PathVariable(value = "carId") int carId)
{

    return new ResponseEntity<>(reviewService.findById(reviewId,carId),HttpStatus.OK);


}
@PutMapping(path="/ridepedia/{carId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto,@PathVariable int reviewId,@PathVariable int carId)
{
    return new ResponseEntity<>(reviewService.updatewReview(reviewDto,reviewId,carId),HttpStatus.OK);
}

@DeleteMapping(path = "/ridepedia/{carId}/reviews/{reviewId}")
    ResponseEntity<String> deleteReview(@PathVariable int carId,@PathVariable int reviewId)
{
    reviewService.deleteReview(reviewId,carId);
    return new ResponseEntity<>("review deleted",HttpStatus.OK);

}

}
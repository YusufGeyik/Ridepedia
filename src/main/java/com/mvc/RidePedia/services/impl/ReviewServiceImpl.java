package com.mvc.RidePedia.services.impl;

import com.mvc.RidePedia.dto.ReviewDto;
import com.mvc.RidePedia.exceptions.CarNotFoundEx;
import com.mvc.RidePedia.exceptions.ReviewNotFoundEx;
import com.mvc.RidePedia.models.Cars;
import com.mvc.RidePedia.models.Reviews;
import com.mvc.RidePedia.repository.CarsRepository;
import com.mvc.RidePedia.repository.ReviewRepository;
import com.mvc.RidePedia.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ReviewServiceImpl implements ReviewService {


    ReviewRepository reviewRepository;
    CarsRepository carsRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CarsRepository carsRepository) {
        this.reviewRepository = reviewRepository;
        this.carsRepository = carsRepository;
    }

    @Override
    public ReviewDto createReview(int carId, ReviewDto reviewDto) {
        Reviews review= mapToEntity(reviewDto);
        Cars car = carsRepository.findById(carId).orElseThrow(()->new CarNotFoundEx("Yorum Yapılamaya çalışılan car bulunamadı."));
        review.setCar(car);
        Reviews newReview=reviewRepository.save(review);
        return maptoDto(newReview);


    }

    @Override
    public ReviewDto findById(int reviewId,int carId) {
        Cars car = carsRepository.findById(carId).orElseThrow(()->new CarNotFoundEx("car not found"));
        ReviewDto reviewDto=new ReviewDto();
        Reviews review=reviewRepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundEx("review could not found"));
        if(review.getCar().getId()!= car.getId())
        {
            throw new ReviewNotFoundEx("this review does not belong to a car");
        }else {
          reviewDto=maptoDto(review);
            return reviewDto;
        }
    }

    @Override
    public List<ReviewDto> getReviewsByCarId(int id) {
        List<ReviewDto> reviewsList= reviewRepository.findByCarId(id).stream().map((review)->maptoDto(review)).collect(Collectors.toList());

        return reviewsList;
    }

    @Override
    public ReviewDto updatewReview(ReviewDto reviewDto,int reviewId,int carId ) {
       Reviews review=reviewRepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundEx("review with associated cars not found"));
       Cars car = carsRepository.findById(carId).orElseThrow(()->new CarNotFoundEx("car with associated review not found"));
       if(review.getCar().getId()!= car.getId())
       {
           throw new ReviewNotFoundEx("review is not belong a cars");
       }
       else {
           review.setContent(reviewDto.getContent());
           review.setTitle(reviewDto.getTitle());
           review.setStars(reviewDto.getStars());
           Reviews updatedReview = reviewRepository.save(review);
           return maptoDto(updatedReview);
       }


    }

    @Override
    public void deleteReview(int reviewId, int carId) {
        Reviews review=reviewRepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundEx("review with associated cars not found"));
        Cars cars = carsRepository.findById(carId).orElseThrow(()->new CarNotFoundEx("cars with associated review not found"));
        if(review.getCar().getId()!= cars.getId())
        {
            throw new ReviewNotFoundEx("this review does not belong to a cars");
        }

        reviewRepository.deleteById(reviewId);


    }

    private ReviewDto maptoDto(Reviews review)
    {
        ReviewDto reviewDto=new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Reviews mapToEntity(ReviewDto reviewDto)
    {
        Reviews review=new Reviews();
        review.setContent(reviewDto.getContent());
        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setId(reviewDto.getId());

        return review;
    }
}

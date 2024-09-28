package com.mvc.RidePedia.services;

import com.mvc.RidePedia.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int carId,ReviewDto reviewDto);
    ReviewDto findById(int reviewId,int carId);
    List<ReviewDto> getReviewsByCarId(int id);

    ReviewDto updatewReview(ReviewDto reviewDto,int reviewId,int carId);

    void deleteReview(int reviewId,int carId);





}

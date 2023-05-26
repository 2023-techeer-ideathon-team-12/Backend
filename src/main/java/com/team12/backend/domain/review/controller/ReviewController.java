package com.team12.backend.domain.review.controller;


import com.team12.backend.domain.review.dto.ReviewListResponseDto;
import com.team12.backend.domain.review.dto.StarCountDto;
import com.team12.backend.domain.review.entity.Reviews;
import com.team12.backend.domain.review.dto.ReviewRequestDto;
import com.team12.backend.domain.review.dto.ReviewResponseDto;
import com.team12.backend.domain.review.mapper.ReviewMapper;
import com.team12.backend.domain.review.repository.ReviewRepository;
import com.team12.backend.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final ReviewMapper reviewMapper;

    private final ReviewRepository  reviewRepository;

    // 리뷰 등록
    @PostMapping()
    public ReviewResponseDto saveReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        Reviews saveReviews = reviewService.saveReviews(reviewRequestDto);
        return reviewMapper.fromEntity(saveReviews);
    }



    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDto reviewRequestDto
    ) {
        Optional<Reviews> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reviews review = optionalReview.get();
        reviewMapper.updateReviewFromEntity(review, reviewRequestDto);
        Reviews updatedReview = reviewRepository.save(review);
        ReviewResponseDto responseDto = reviewMapper.fromEntity(updatedReview);

        return ResponseEntity.ok(responseDto);
    }


    // 리뷰 조회
    @GetMapping("/gym/{gymId}")
    public ResponseEntity<List<ReviewListResponseDto>> getAllReviewsByGymId(@PathVariable Long gymId) {
        List<ReviewListResponseDto> reviewList = reviewService.getAllReviewsByGymId(gymId);
        return ResponseEntity.ok(reviewList);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReviewById(reviewId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/star/{gymId}")
    public ResponseEntity<List<StarCountDto>> getStarCountsByGymId(@PathVariable Long gymId) {
        List<StarCountDto> starCounts = reviewService.getStarCountsByGymId(gymId);
        return ResponseEntity.ok(starCounts);
    }





}

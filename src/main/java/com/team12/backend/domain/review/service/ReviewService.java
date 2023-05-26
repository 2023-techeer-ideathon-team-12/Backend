package com.team12.backend.domain.review.service;

import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.domain.gym.repository.GymRepository;
import com.team12.backend.domain.review.dto.ReviewListResponseDto;
import com.team12.backend.domain.review.dto.ReviewResponseDto;
import com.team12.backend.domain.review.dto.StarCountDto;
import com.team12.backend.domain.review.entity.Reviews;
import com.team12.backend.domain.review.dto.ReviewRequestDto;
import com.team12.backend.domain.review.mapper.ReviewMapper;
import com.team12.backend.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapper  reviewMapper;

    private final GymRepository gymRepository;


    // 리뷰 올리기 - 체육관별
    @Transactional
    public Reviews saveReviews(ReviewRequestDto reviewRequestDto) {
        Optional<Gym> optionalGym = gymRepository.findById(reviewRequestDto.getGymId());
        Gym findGym = optionalGym.orElse(null);

        Reviews reviews = reviewMapper.toEntity(reviewRequestDto, findGym);
        reviewRepository.save(reviews);

        updateGymStarAvg(findGym); // Update gym's starAvg value

        return reviews;
    }

    private void updateGymStarAvg(Gym gym) {
        List<Reviews> reviews = reviewRepository.findByGym(gym);
        double totalStars = 0;
        int count = reviews.size();

        for (Reviews review : reviews) {
            totalStars += review.getStar();
        }

        double starAvg = count > 0 ? totalStars / count : 0;
        gym.setStarAvg(starAvg);
        gymRepository.save(gym);
    }


    // 리뷰 조회 - 체육관 별
    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> getAllReviewsByGymId(Long gymId) {
        Gym gym = gymRepository.findById(gymId)
            .orElseThrow(() -> new NoSuchElementException("No gym found"));

        List<Reviews> reviews = reviewRepository.findByGym(gym);
        return reviews.stream()
            .map(reviewMapper::fromListEntity)
            .collect(Collectors.toList());
    }

    // 리뷰 수정하기
    @Transactional
    public ReviewResponseDto updateReview(Long revewId, ReviewRequestDto reviewRequestDto) {
        Optional<Reviews> optionalReview = reviewRepository.findById(revewId);

        if (optionalReview.isEmpty()) {
            throw new NoSuchElementException("No review found");
        }

        Reviews review = optionalReview.get();
        reviewMapper.updateReviewFromEntity(review, reviewRequestDto);
        Reviews updatedReview = reviewRepository.save(review);

        return reviewMapper.fromEntity(updatedReview);
    }


    // 리뷰 삭제하기
    @Transactional
    public void deleteReviewById(Long reviewId) {
        Reviews review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new NoSuchElementException("No review found"));

        reviewRepository.delete(review);
    }


    // 별점 수 계산
    @Transactional(readOnly = true)
    public List<StarCountDto> getStarCountsByGymId(Long gymId) {
        List<StarCountDto> starCounts = new ArrayList<>();

        for (int star = 5; star >= 1; star--) {
            int count = reviewRepository.countByGymIdAndStar(gymId, star);
            StarCountDto starCount = new StarCountDto(star, count);
            starCounts.add(starCount);
        }

        return starCounts;
    }



}
    
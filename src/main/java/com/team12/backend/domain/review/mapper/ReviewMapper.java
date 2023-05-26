package com.team12.backend.domain.review.mapper;

import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.domain.review.dto.ReviewListResponseDto;
import com.team12.backend.domain.review.entity.Reviews;
import com.team12.backend.domain.review.dto.ReviewRequestDto;
import com.team12.backend.domain.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public Reviews toEntity(ReviewRequestDto reviewRequestDto, Gym gym ){
        return Reviews.builder()
                .gym(gym)
                .content(reviewRequestDto.getContent())
                .star(reviewRequestDto.getStar())
                .build();
    }

    public ReviewResponseDto fromEntity(Reviews reviews){
        return ReviewResponseDto.builder()
                .reviewId(reviews.getReviewId())
                .gymId(reviews.getGym().getId())  // 체육관 id 가져오기
                .content(reviews.getContent())
                .star(reviews.getStar())
                .createAt(reviews.getCreateAt() != null ? reviews.getCreateAt().toString() : null)
                .updateAt(reviews.getUpdateAt() != null ? reviews.getUpdateAt().toString() : null)
                .build();
    }


    public ReviewListResponseDto fromListEntity(Reviews reviews){
        return ReviewListResponseDto.builder()
//                .reviewId(reviews.getReviewId())
//                .gymId(reviews.getGym().getId())  // 체육관 id 가져오기
                .content(reviews.getContent())
                .star(reviews.getStar())
                .createAt(reviews.getCreateAt() != null ? reviews.getCreateAt().toString() : null)
//                .updateAt(reviews.getUpdateAt() != null ? reviews.getUpdateAt().toString() : null)
                .build();
    }


    public void updateReviewFromEntity(Reviews reviews, ReviewRequestDto reviewRequestDto){
        reviews.setContent(reviewRequestDto.getContent());
        reviews.setStar(reviewRequestDto.getStar());
    }


}


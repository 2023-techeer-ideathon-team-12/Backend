package com.team12.backend.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReviewListResponseDto {

    private Long reviewId;
    private String content;

    private int star;

    private String createAt;

}

package com.team12.backend.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {

    private Long gymId;

    private String content;

    private int star;
}

package com.team12.backend.domain.gym.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ResponseDto {
    private Long id;
    private String name;
    private String address;
    private Integer currentUser;
    private Integer area;
    private String machine;
    private Double starAvg;
    private String thumbnailUrl;
    private Double latitude;
    private Double longitude;
    private String status;

}
package com.team12.backend.domain.gym.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UpdateCurrentUserDto {
    private Long id;
    private Integer currentUser;

}
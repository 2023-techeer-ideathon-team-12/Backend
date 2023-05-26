package com.team12.backend.domain.gym.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LocationDto {

    private double latitude;
    private double longitude;

    public LocationDto() {
    }

    public LocationDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
package com.team12.backend.domain.gym.controller;

import com.team12.backend.domain.gym.dto.LocationDto;
import com.team12.backend.domain.gym.dto.ResponseDto;
import com.team12.backend.domain.gym.dto.UpdateCurrentUserDto;
import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.domain.gym.service.GymService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/gyms")
public class GymController {

    private final GymService gymService;
    @PostMapping("{id}/increase")
    public ResponseEntity<UpdateCurrentUserDto> increaseCurrentUser(@PathVariable("id") Long id) {
        int updateCurrentUser = gymService.increaseUser(id);

        UpdateCurrentUserDto updateCurrentUserDto = UpdateCurrentUserDto.builder()
            .id(id)
            .currentUser(updateCurrentUser)
            .build();

        return ResponseEntity.ok(updateCurrentUserDto);
    }

    @PostMapping("{id}/decrease")
    public ResponseEntity<UpdateCurrentUserDto> decreaseCurrentUser(@PathVariable("id") Long id) {
        int updateCurrentUser = gymService.decreaseUser(id);

        UpdateCurrentUserDto updateCurrentUserDto = UpdateCurrentUserDto.builder()
            .id(id)
            .currentUser(updateCurrentUser)
            .build();

        return ResponseEntity.ok(updateCurrentUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getGymDetails(@PathVariable("id") Long id) {
        ResponseDto responseDto = gymService.getGymDetails(id);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/current")
    public ResponseEntity<List<Gym>> getCurrentLocation(@RequestBody LocationDto locationDto) {
        double plusLatitude = locationDto.getLatitude() + 500;
        double plusLongitude = locationDto.getLongitude() + 500;
        double minusLatitude = locationDto.getLatitude() - 500;
        double minusLongitude = locationDto.getLongitude() - 500;

        List<Gym> gyms = gymService.getGymsByLocationRange(plusLatitude, plusLongitude, minusLatitude, minusLongitude);

        return ResponseEntity.ok(gyms);
    }
}

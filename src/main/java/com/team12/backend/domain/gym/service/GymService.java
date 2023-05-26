package com.team12.backend.domain.gym.service;

import com.team12.backend.domain.gym.dto.ResponseDto;
import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.domain.gym.repository.GymRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GymService {
    private final GymRepository gymRepository;

    public int increaseUser(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(RuntimeException::new);
        gym.increaseCurrentUser();
        gymRepository.save(gym);
        return gym.getCurrentUser();
    }

    public int decreaseUser(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(RuntimeException::new);
        gym.decreaseCurrentUser();
        gymRepository.save(gym);
        return gym.getCurrentUser();
    }

    public ResponseDto getGymDetails(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(RuntimeException::new);
        String status = calculateStatus(gym.getArea(), gym.getCurrentUser());
        return ResponseDto.builder()
            .id(gym.getId())
            .name(gym.getName())
            .address(gym.getAddress())
            .currentUser(gym.getCurrentUser())
            .area(gym.getArea())
            .machine(gym.getMachine())
            .starAvg(gym.getStarAvg())
            .thumbnailUrl(gym.getThumbnailUrl())
            .latitude(gym.getLatitude())
            .longitude(gym.getLongitude())
            .status(status)
            .build();
    }

    public List<Gym> getGymsByLocationRange(double plusLatitude, double plusLongitude, double minusLatitude, double minusLongitude) {
        List<Long> gymIds = gymRepository.findIdsByLatitudeBetweenAndLongitudeBetween(plusLatitude, plusLongitude, minusLatitude, minusLongitude);

        return gymRepository.findAllById(gymIds);
    }

    public List<ResponseDto> getAllGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream()
            .map(this::mapToGymDto)
            .collect(Collectors.toList());
    }

    private ResponseDto mapToGymDto(Gym gym) {
        String status = calculateStatus(gym.getArea(), gym.getCurrentUser());
        return ResponseDto.builder()
            .id(gym.getId())
            .name(gym.getName())
            .address(gym.getAddress())
            .currentUser(gym.getCurrentUser())
            .area(gym.getArea())
            .machine(gym.getMachine())
            .starAvg(gym.getStarAvg())
            .thumbnailUrl(gym.getThumbnailUrl())
            .latitude(gym.getLatitude())
            .longitude(gym.getLongitude())
            .status(status)
            .build();
    }

    private String calculateStatus(Integer area, Integer currentUser) {
        int areaAll = area / currentUser;
        if (areaAll <= 1) {
            return "혼잡";
        } else if (areaAll <= 3) {
            return "보통";
        } else {
            return "여유";
        }
    }
}
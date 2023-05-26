package com.team12.backend.domain.gym.repository;

import com.team12.backend.domain.gym.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}

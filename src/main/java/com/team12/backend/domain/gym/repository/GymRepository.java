package com.team12.backend.domain.gym.repository;

import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.domain.review.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Long> {

}

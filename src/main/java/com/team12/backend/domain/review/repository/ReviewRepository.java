package com.team12.backend.domain.review.repository;

import com.team12.backend.domain.gym.entity.Gym;
import com.team12.backend.domain.review.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {

//    Optional<Reviews> findById(Long reviewId);
      List<Reviews> findByGym(Gym gym);

      int countByGymIdAndStar(Long gymId, int star);
}

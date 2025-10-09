package com.example._mission.repository;

import com.example._mission.domain.Review;
import com.example._mission.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUserOrderByCreatedAtDesc(User user);
}
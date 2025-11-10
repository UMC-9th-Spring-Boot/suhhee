package com.example._mission.repository;

import com.example._mission.domain.UserFoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodPreferenceRepository extends JpaRepository<UserFoodPreference, Long> {
}
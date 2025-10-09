package com.example._mission.repository;

import com.example._mission.domain.User;
import com.example._mission.domain.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 진행중인 미션 조회
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m " +
            "WHERE um.user = :user AND um.totalCount < m.requiredCount")
    Page<UserMission> findOngoingMissions(@Param("user") User user, Pageable pageable);

    // 완료된 미션 조회
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m " +
            "WHERE um.user = :user AND um.totalCount >= m.requiredCount")
    Page<UserMission> findCompletedMissions(@Param("user") User user, Pageable pageable);
}
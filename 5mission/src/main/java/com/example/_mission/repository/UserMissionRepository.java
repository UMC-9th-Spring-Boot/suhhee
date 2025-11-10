package com.example._mission.repository;

import com.example._mission.domain.User;
import com.example._mission.domain.UserMission;
import com.example._mission.domain.UserMissionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//                                                                    PK 아니고 user 과 mission 합친 복합키임..
public interface UserMissionRepository extends JpaRepository<UserMission, UserMissionId> {

    // 진행중인 미션 조회
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m " +
            "WHERE um.user = :user AND um.totalCount < m.requiredCount")
    Page<UserMission> findOngoingMissions(@Param("user") User user, Pageable pageable);

    // 완료된 미션 조회
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m " +
            "WHERE um.user = :user AND um.totalCount >= m.requiredCount")
    Page<UserMission> findCompletedMissions(@Param("user") User user, Pageable pageable);
}
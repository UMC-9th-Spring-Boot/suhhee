package com.example._mission.converter;

import com.example._mission.domain.Mission;
import com.example._mission.domain.User;
import com.example._mission.domain.UserMission;
import com.example._mission.dto.MissionResDTO;

public class MissionConverter {

    // (User, Mission) -> UserMission 엔티티로 변환
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .totalCount(0) // 새로 하는 도전이기에.. 카운트는 0 (?)
                .build();
    }

    // UserMission -> 응답 DTO
    public static MissionResDTO.ChallengeMissionDTO toChallengeMissionDTO(UserMission userMission) {
        return MissionResDTO.ChallengeMissionDTO.builder()
                .userId(userMission.getUser().getId())
                .missionId(userMission.getMission().getId())
                .totalCount(userMission.getTotalCount())
                .build();
    }
}
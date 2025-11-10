package com.example._mission.service.command;

import com.example._mission.dto.MissionResDTO;

// 미션 변경 관련 서비스
public interface MissionCommandService {

    // 미션 도전하기 기능
    MissionResDTO.ChallengeMissionDTO challengeMission(Long missionId);
}
package com.example._mission.service.query;

import com.example._mission.dto.MissionResDTO;

public interface MissionQueryService {
    // 가게 아이디로 미션 목록 조회
    MissionResDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page);

    // 나의 진행중 미션 목록
    MissionResDTO.MyMissionListDTO getMyOngoingMissions(Long userId, Integer page);
}
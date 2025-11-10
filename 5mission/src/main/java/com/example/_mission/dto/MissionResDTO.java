package com.example._mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 미션 관련 응답 DTO를 모아두는 클래스임
public class MissionResDTO {

    // 미션 도전하기 성공 시 -> 응답 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionDTO {
        private Long userId;    // 어느 유저가
        private Long missionId; // 어떤 미션을
        private Integer totalCount; // 몇 번 도전했는지 (초기값은 0임)
    }
}
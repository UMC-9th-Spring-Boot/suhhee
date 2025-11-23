package com.example._mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
        private Integer totalCount; // 몇 번 도전했는지 (초기값은 0)
    }

    // 미션 목록 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionPreViewDTO> missionList; // 미션 리스트
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    //  미션 내용
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        Long missionId;
        Integer reward;     // 적립 포인트
        LocalDate deadline; // 마감 기한
        String missionSpec; // 미션 내용
    }

    // 내가 진행 중인 미션 목록
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionListDTO {
        List<MyMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    // 내가 진행 중인 미션 내용
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDTO {
        Long missionId;
        String storeName;
        String missionSpec; // 미션 내용
        Integer reward;     // 보상
        Integer totalCount; // 현재 달성 횟수
        LocalDate deadline;
    }
}
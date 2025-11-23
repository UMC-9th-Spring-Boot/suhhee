package com.example._mission.converter;

import com.example._mission.domain.Mission;
import com.example._mission.domain.User;
import com.example._mission.domain.UserMission;
import com.example._mission.dto.MissionResDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    // 1. Mission -> DTO
    public static MissionResDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResDTO.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .reward(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionTitle())
                .build();
    }

    // 2. Page<Mission> -> ListDTO (목록)
    public static MissionResDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionPage) {

        List<MissionResDTO.MissionPreViewDTO> missionPreViewDTOList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionPreViewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

    // UserMission -> MyMissionDTO (내 미션 조회)
    public static MissionResDTO.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getStoreName()) // 미션 -> 가게 -> 이름
                .missionSpec(userMission.getMission().getMissionTitle()) // 미션 내용
                .reward(userMission.getMission().getRewardPoints())
                .totalCount(userMission.getTotalCount())
                .deadline(userMission.getMission().getDeadline())
                .build();
    }

    // Page<UserMission> -> MyMissionListDTO
    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<UserMission> userMissionPage) {

        List<MissionResDTO.MyMissionDTO> myMissionDTOList = userMissionPage.getContent().stream()
                .map(MissionConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MyMissionListDTO.builder()
                .isLast(userMissionPage.isLast())
                .isFirst(userMissionPage.isFirst())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .listSize(myMissionDTOList.size())
                .missionList(myMissionDTOList)
                .build();
    }
}
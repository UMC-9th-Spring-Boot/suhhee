package com.example._mission.service.command;

import com.example._mission.converter.MissionConverter;
import com.example._mission.domain.Mission;
import com.example._mission.domain.User;
import com.example._mission.domain.UserMission;
import com.example._mission.domain.UserMissionId;
import com.example._mission.dto.MissionResDTO;
import com.example._mission.global.apiPayload.code.GeneralErrorCode;
import com.example._mission.global.apiPayload.exception.GeneralException;
import com.example._mission.global.apiPayload.code.MissionErrorCode;
import com.example._mission.global.apiPayload.exception.MissionException;
import com.example._mission.repository.MissionRepository;
import com.example._mission.repository.UserMissionRepository;
import com.example._mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    // 필요한 리포지토리 주입
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public MissionResDTO.ChallengeMissionDTO challengeMission(Long missionId) {
        Long hardcodedUserId = 1L;

        User user = userRepository.findById(hardcodedUserId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        // URL로 받은 missionId로 미션 찾음
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND)); // Mission 에러 코드를 만들어도 좋아

        // 이미 도전 중인 미션인지 확인!!
        // UserMissionId 객체(복합 키)로 DB에서 조회
        UserMissionId userMissionId = new UserMissionId(hardcodedUserId, missionId);
        if (userMissionRepository.findById(userMissionId).isPresent()) {
            // 이미 존재하면 에러 발생
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING);
        }

        // 컨버터를 사용해 UserMission 엔티티 생성
        UserMission newUserMission = MissionConverter.toUserMission(user, mission);

        // UserMission 저장
        UserMission savedUserMission = userMissionRepository.save(newUserMission);

        // 컨버터를 사용해 응답 DTO로 변환 후 반환
        return MissionConverter.toChallengeMissionDTO(savedUserMission);
    }
}
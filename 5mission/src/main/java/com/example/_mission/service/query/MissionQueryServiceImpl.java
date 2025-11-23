package com.example._mission.service.query;

import com.example._mission.repository.UserMissionRepository;
import com.example._mission.repository.UserRepository;
import com.example._mission.domain.enums.MissionStatus;
import com.example._mission.domain.UserMission;
import com.example._mission.domain.User;
import com.example._mission.converter.MissionConverter;
import com.example._mission.domain.Mission;
import com.example._mission.domain.Store;
import com.example._mission.dto.MissionResDTO;
import com.example._mission.global.apiPayload.code.StoreErrorCode;
import com.example._mission.global.apiPayload.exception.StoreException;
import com.example._mission.repository.MissionRepository;
import com.example._mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    public MissionResDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page) {

        // 가게 여부
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 페이징 생성, 0부터 시작
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 조회
        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        // 변환
        return MissionConverter.toMissionPreViewListDTO(missionPage);
    }

    @Override
    public MissionResDTO.MyMissionListDTO getMyOngoingMissions(Long userId, Integer page) {

        // 유저 여부
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        // 페이징
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 진행 중인 미션만 조회
        Page<UserMission> userMissionPage = userMissionRepository.findAllByUserAndStatus(user, MissionStatus.CHALLENGING, pageRequest);

        // 변환
        return MissionConverter.toMyMissionListDTO(userMissionPage);
    }
}
package com.example._mission.service.command;

import com.example._mission.converter.UserConverter;
import com.example._mission.domain.FoodCategory;
import com.example._mission.domain.User;
import com.example._mission.domain.UserFoodPreference;
import com.example._mission.dto.UserReqDTO;
import com.example._mission.dto.UserResDTO;
import com.example._mission.global.apiPayload.code.FoodCategoryErrorCode;
import com.example._mission.global.apiPayload.exception.FoodCategoryException;
import com.example._mission.repository.FoodCategoryRepository;
import com.example._mission.repository.UserFoodPreferenceRepository;
import com.example._mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final UserFoodPreferenceRepository userFoodPreferenceRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    // 회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        User user = UserConverter.toUser(dto);
        // DB 적용
        userRepository.save(user);

        // 선호 음식 존재 여부 확인
        if (dto.getPreferCategory() != null && !dto.getPreferCategory().isEmpty()){

            List<UserFoodPreference> userFoodPreferenceList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.getPreferCategory()) {
                FoodCategory foodCategory = foodCategoryRepository.findById(id)
                        .orElseThrow(() -> new FoodCategoryException(FoodCategoryErrorCode.NOT_FOUND));

                // UserFoodPreference 엔티티 생성
                UserFoodPreference userFoodPreference = UserFoodPreference.builder()
                        .user(user)
                        .foodCategory(foodCategory)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                userFoodPreferenceList.add(userFoodPreference);
            }
            // 모든 선호 음식 추가: DB 적용
            userFoodPreferenceRepository.saveAll(userFoodPreferenceList);
        }

        // 응답 DTO 생성
        return UserConverter.toJoinDTO(user);
    }
}
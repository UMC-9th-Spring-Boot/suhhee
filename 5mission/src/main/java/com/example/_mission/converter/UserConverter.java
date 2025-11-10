package com.example._mission.converter;

import com.example._mission.domain.FoodCategory;
import com.example._mission.domain.User;
import com.example._mission.domain.UserFoodPreference;
import com.example._mission.dto.UserReqDTO;
import com.example._mission.dto.UserResDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    // DTO -> Entity (회원가입 요청을 실제 User 엔티티로 변환)
    public static User toUser(UserReqDTO.JoinDTO dto) {

        return User.builder()
                .userName(dto.getUserName())
                .userPw(dto.getUserPw())
                .userPhone(dto.getUserPhone())
                .gender(dto.getGender())
                .userBirth(dto.getUserBirth())
                .userEmail(dto.getUserEmail())
                .createAt(LocalDateTime.now()) // 생성 시간
                .status("ACTIVE") // 기본 상태 설정
                .currentPoints(0) // 기본 포인트 설정
                .build();
    }

    // Entity -> DTO (회원가입 완료된 User 엔티티를 응답 DTO로 변환)
    public static UserResDTO.JoinDTO toJoinDTO(User user) {
        return UserResDTO.JoinDTO.builder()
                .userId(user.getId())
                .createAt(user.getCreateAt())
                .build();
    }
}
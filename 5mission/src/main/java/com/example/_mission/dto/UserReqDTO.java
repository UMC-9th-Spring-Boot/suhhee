// 회원가입 시 클라이언트가 서버로 보낼 데이터를 담는 클래스
package com.example._mission.dto;

import com.example._mission.global.annotation.ExistFoodCategories;
import com.example._mission.domain.User; // User 엔티티의 Gender Enum 사용
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

// User 관련 DTO를 모아두는 클래스
public class UserReqDTO {

    @Getter
    public static class JoinDTO {
        String userName;
        String userPw;
        String userPhone;
        User.Gender gender; // User 엔티티에 정의된 Gender Enum
        LocalDateTime userBirth;
        String userEmail;

        @ExistFoodCategories
        List<Long> preferCategory;
    }
}
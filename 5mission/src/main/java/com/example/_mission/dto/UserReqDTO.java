// 회원가입 시 클라이언트가 서버로 보낼 데이터를 담는 클래스
package com.example._mission.dto;

import com.example._mission.global.annotation.ExistFoodCategories;
import com.example._mission.domain.User; // User 엔티티의 Gender Enum 사용
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

// User 관련 DTO를 모아두는 클래스
public class UserReqDTO {

    @Getter
    public static class JoinDTO {
        @NotBlank
        String userName;
        @Email
        String email;
        @NotBlank
        String userPw;
        String userPhone;
        @NotNull
        User.Gender gender;
        LocalDateTime userBirth;

        @ExistFoodCategories
        List<Long> preferCategory;
    }
    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
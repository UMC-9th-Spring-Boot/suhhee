// 회원가입 성공 시 서버가 클라이언트에게 성공했다고 보내줄 데이터를 담는 클래스
package com.example._mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// User 관련 응답 DTO를 모아두는 클래스
public class UserResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDTO {
        Long userId;
        LocalDateTime createAt;
    }
}
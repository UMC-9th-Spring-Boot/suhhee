package com.example._mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 리뷰 관련 응답 DTO를 모아두는 클래스
public class ReviewResDTO {

    // 리뷰 등록 성공 시 반환할 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddReviewDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }
}
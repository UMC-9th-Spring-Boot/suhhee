package com.example._mission.dto;

import lombok.Getter;

// 리뷰 관련 요청 DTO를 모아두는 클래스
public class ReviewReqDTO {
    @Getter
    public static class AddReviewDTO {
        private Float star;
        private String content;
    }
}
package com.example._mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// 리뷰 관련 응답 DTO를 모아둠!
public class ReviewResDTO {

    // 리뷰 목록과 페이징 정보를 담고 있는 최종 응답 List
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList; // 리뷰 데이터 목록
        Integer listSize;          // 현 페이지에 있는 리뷰 개수
        Integer totalPage;         // 전체 페이지 수
        Long totalElements;        // 전체 리뷰 개수
        Boolean isFirst;           // 첫 번째 페이지 여부
        Boolean isLast;            // 마지막 페이지 여부
    }

    // 리뷰 하나하나의 정보를 담을 Item
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {  // 날짜만
        String ownerNickname;      // 작성자 닉네임 (Member 엔티티에서 가져옴)
        Float score;               // 별점
        String body;               // 리뷰 내용
        LocalDate createdAt;       // 작성 날짜
    }

    // 리뷰 등록 성공 시 반환할 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddReviewDTO {  // 날짜 + 시간
        private Long reviewId;
        private LocalDateTime createdAt;
    }
}
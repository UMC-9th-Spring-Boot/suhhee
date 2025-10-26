// ReviewDto.java

package com.example._mission.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 내가 쓴 리뷰 조회용 DTO
 * API 응답으로 사용할 객체입니다.
 */
@Getter
@Setter // (1) QueryDSL Projections가 값을 주입할 때 Setter가 필요
@NoArgsConstructor(access = AccessLevel.PUBLIC) // (2) Projections가 객체를 생성할 기본 생성자 필요
public class ReviewDto {

    // (3) 내가 API 응답으로 보내고 싶은 필드들
    private Long reviewId;      // 리뷰 ID
    private String storeName;   // 가게 이름
    private String content;     // 리뷰 내용
    private Float star;         // 별점
    private LocalDateTime createdAt; // 작성일
}
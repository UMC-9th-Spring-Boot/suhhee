package com.example._mission.converter;

import com.example._mission.domain.Review;
import com.example._mission.domain.Store;
import com.example._mission.domain.User;
import com.example._mission.dto.ReviewReqDTO;
import com.example._mission.dto.ReviewResDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    // DTO -> Entity 변환
    // User, Store 엔티티는 서비스에서 찾아서 넣어줌
    public static Review toReview(ReviewReqDTO.AddReviewDTO dto, User user, Store store) {
        return Review.builder()
                .star(dto.getStar())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now()) // 생성 시간은 지금
                .user(user)   // 서비스에서 찾아온 User
                .store(store) // 서비스에서 찾아온 Store
                .build();
    }

    // 2. Entity -> DTO 변환 (응답용)
    public static ReviewResDTO.AddReviewDTO toAddReviewDTO(Review review) {
        return ReviewResDTO.AddReviewDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
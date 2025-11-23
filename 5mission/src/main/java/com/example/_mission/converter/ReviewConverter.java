package com.example._mission.converter;

import com.example._mission.domain.Review;
import com.example._mission.domain.Store;
import com.example._mission.domain.User;
import com.example._mission.dto.ReviewReqDTO;
import com.example._mission.dto.ReviewResDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    // Entity -> DTO 변환 (응답용)
    public static ReviewResDTO.AddReviewDTO toAddReviewDTO(Review review) {
        return ReviewResDTO.AddReviewDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
    // 리뷰(Entity) -> 리뷰 미리보기 DTO
    public static ReviewResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getNickname())  // JPA가 테이블의 연관관계를 파악하고 연관성이 있으면 불러올 수 있는 기능을 제공하기에 member 요소를 가져올 수 있는거.. (워크북 9장)
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    // 리뷰 페이지(Page<Review>) -> 리뷰 목록 응답 DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewPage){

        // Review들 -> DTO로 변환 (Stream 사용)
        List<ReviewResDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewPage.isLast())
                .isFirst(reviewPage.isFirst())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
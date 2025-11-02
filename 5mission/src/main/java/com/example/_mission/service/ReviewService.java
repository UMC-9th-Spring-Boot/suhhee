package com.example._mission.service;

import com.example._mission.domain.QReview;
import com.example._mission.domain.User; //User 엔티티 import
import com.example._mission.dto.ReviewDto;
import com.example._mission.global.apiPayload.code.GeneralErrorCode; //에러 코드 import
import com.example._mission.global.apiPayload.exception.GeneralException; //예외 import
import com.example._mission.repository.ReviewRepository;
import com.example._mission.repository.UserRepository; //UserRepository import

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository; // 5. UserRepository 주입
    private static final QReview review = QReview.review;

    // 반환 타입은 Page<ReviewDto>로 동일
    public Page<ReviewDto> getMyReviews(Long userId, String storeName, Integer starRating, Pageable pageable) {

        // userId로 실제 User 객체를 조회
        // findById를 시도하고 .orElseThrow()를 사용해
        // 만약 유저가 없으면(null이면) GeneralException을 던짐
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.user.eq(user));

        if (StringUtils.hasText(storeName)) {
            // QStore.store를 사용하도록 QReview.review.store.storeName로
            builder.and(review.store.storeName.eq(storeName));
        }

        if (starRating != null) {
            if (starRating == 5) {
                builder.and(review.star.eq(5.0f));
            } else {
                builder.and(review.star.goe(starRating.floatValue()))
                        .and(review.star.lt(starRating.floatValue() + 1.0f));
            }
        }

        // 반환 타입 Page<ReviewDto>로 자동 변경
        return reviewRepository.findMyReviews(builder, pageable);
    }
}
// ReviewService.java

package com.example._mission.service;

import com.example._mission.domain.QReview;
import com.example._mission.dto.ReviewDto;
import com.example._mission.repository.ReviewRepository;

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
    private static final QReview review = QReview.review;

    // 반환 타입 Page<ReviewDto>
    public Page<ReviewDto> getMyReviews(Long userId, String storeName, Integer starRating, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.user.id.eq(userId));

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

        // 반환 타입 Page<ReviewDto>로 자동 변경됨
        return reviewRepository.findMyReviews(builder, pageable);
    }
}

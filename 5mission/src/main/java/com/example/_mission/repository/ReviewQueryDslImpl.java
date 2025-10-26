// ReviewQueryDslImpl.java

package com.example._mission.repository;

import com.example._mission.domain.QReview;
import com.example._mission.domain.QStore;   // 가게 이름을 가져오기 위해 QStore import
import com.example._mission.dto.ReviewDto;   // DTO import
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections; // Projections import
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    private static final QReview review = QReview.review;
    private static final QStore store = QStore.store; // QStore 객체 생성

    // 반환 타입 Page<ReviewDto>
    @Override
    public Page<ReviewDto> findMyReviews(Predicate predicate, Pageable pageable) {

        // --- 데이터 조회 쿼리 ---
        List<ReviewDto> content = queryFactory
                .select(Projections.bean(ReviewDto.class,
                        review.id.as("reviewId"), // DTO 필드명과 맞춤
                        store.storeName.as("storeName"), // 가게 이름 매핑
                        review.content,
                        review.star,
                        review.createdAt
                ))
                .from(review)
                // 가게 이름을 가져오기 위해 Store와 JOIN
                .leftJoin(review.store, store)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); // List<ReviewDto>로 반환

        // --- 총 개수 조회 쿼리 ---
        // (Count 쿼리는 JOIN이 필요 없을 수 있으므로, 별도로 최적화)
        Long totalCount = queryFactory
                .select(review.count())
                .from(review)
                //  Service의 BooleanBuilder가 storeName 조건을 사용했다면
                //  Count 쿼리에도 동일한 JOIN이 필요함
                .leftJoin(review.store, store) // Service의 storeName 필터링을 위해 JOIN
                .where(predicate)
                .fetchOne();

        // --- Page 객체로 변경 후 반환 ---
        return new PageImpl<>(content, pageable, totalCount);
    }
}
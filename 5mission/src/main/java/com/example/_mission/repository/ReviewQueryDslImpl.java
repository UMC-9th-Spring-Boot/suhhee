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
                // (1) SELECT 절: 어떤 데이터를 가져올지 선택
                .select(Projections.bean(ReviewDto.class, // 가져온 데이터를 ReviewDto 객체로 변환
                        review.id.as("reviewId"),      // review 테이블의 id를 reviewId 필드에
                        store.storeName.as("storeName"),// store 테이블의 storeName을 storeName 필드에
                        review.content,                 // review 테이블의 content를 content 필드에
                        review.star,                    // review 테이블의 star를 star 필드에
                        review.createdAt                // review 테이블의 createdAt을 createdAt 필드에
                ))
                // (2) FROM 절: 어느 테이블에서 시작할지 지정
                .from(review) // review 테이블에서 시작
                // (3) JOIN 절: 다른 테이블과 연결
                .leftJoin(review.store, store) // review 테이블과 store 테이블을 연결 (가게 이름 필요)
                // (4) WHERE 절: 어떤 조건으로 필터링할지 지정
                .where(predicate) // Service에서 만들어준 동적 조건 (예: user_id=1 AND star >= 4 AND star < 5)
                // (5) ORDER BY 절: 어떤 순서로 정렬할지 지정
                .orderBy(review.createdAt.desc()) // 최신 작성순으로 정렬
                // (6) OFFSET 절: 몇 개의 데이터를 건너뛸지 지정 (페이징)
                .offset(pageable.getOffset()) // 예: 0페이지면 0개, 1페이지면 10개 건너뛰기
                // (7) LIMIT 절: 몇 개의 데이터를 가져올지 지정 (페ING)
                .limit(pageable.getPageSize()) // 예: 10개만 가져오기
                // (8) 실행 및 결과 반환
                .fetch(); // 위에서 정의한 쿼리를 실행하고 결과를 List<ReviewDto>로 받음

        // --- 총 개수 조회 쿼리 ---
        Long totalCount = queryFactory
                // (1) SELECT 절: 무엇을 셀지 지정
                .select(review.count()) // review 테이블의 row 개수를 세어줘
                // (2) FROM 절: 어느 테이블에서 셀지 지정
                .from(review) // review 테이블에서
                // (3) JOIN 절: WHERE 조건에 필요하면 추가
                .leftJoin(review.store, store) // Service의 storeName 필터링 때문에 JOIN 필요
                // (4) WHERE 절: 데이터 조회와 동일한 조건 적용
                .where(predicate) // 데이터 조회 쿼리와 동일한 동적 조건 사용
                // (5) 실행 및 결과 반환 (단일 값)
                .fetchOne(); // 쿼리 실행 결과 (숫자 하나)를 Long 타입으로 받음

        // --- Page 객체로 변경 후 반환 ---
        // content : 현재 페이지의 데이터 목록 (List<ReviewDto>)
        // pageable : Controller에서 넘겨받은 원본 페이징 요청 정보
        //              (Pageable 객체, 여기엔 몇 페이지를 요청했는지, 페이지 크기는 얼마인지 등이 들어있음)
        // totalCount : 필터링된 전체 데이터 개수 (Long)
        return new PageImpl<>(content, pageable, totalCount);
    }
}
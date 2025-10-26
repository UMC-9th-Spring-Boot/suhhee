// ReviewQueryDsl.java

package com.example._mission.repository;

import com.example._mission.dto.ReviewDto; // DTO import
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {


    // 반환 타입을 Page<ReviewDto>
    Page<ReviewDto> findMyReviews(Predicate predicate, Pageable pageable);
}
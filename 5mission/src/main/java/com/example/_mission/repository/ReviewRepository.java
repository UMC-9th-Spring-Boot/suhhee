// ReviewRepository.java

package com.example._mission.repository;

import com.example._mission.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Review 엔티티를 위한 Spring Data JPA 리포지토리
 * JpaRepository: save, findById 등 기본 CRUD 기능 제공
 * ReviewQueryDsl: 내가 정의한 QueryDSL 기능 제공
 */
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // JpaRepository와 ReviewQueryDsl의 모든 메서드를 사용할 수 있음
}
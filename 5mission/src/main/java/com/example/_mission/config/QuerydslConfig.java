// QuerydslConfig.java

package com.example._mission.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueryDSL을 사용하기 위한 설정 클래스
 * JPAQueryFactory를 Spring Bean으로 등록
 */
@Configuration
public class QuerydslConfig {

    // EntityManager를 주입받음 (JPA의 핵심 객체)
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * JPAQueryFactory를 Bean으로 등록
     * 이 Bean을 통해 QueryDSL 쿼리를 실행할 수 있음
     * @return JPAQueryFactory 인스턴스
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
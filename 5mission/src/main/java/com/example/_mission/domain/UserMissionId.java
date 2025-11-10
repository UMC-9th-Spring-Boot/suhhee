package com.example._mission.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserMission 엔티티의 복합 키를 정의하기 위한 "헬퍼 클래스"
 * @ IdClass로 사용되려면 3가지 조건을 지켜야됨
 * 1. public 클래스여야됨
 * 2. Serializable 인터페이스를 구현
 * 3. 기본 생성자(NoArgsConstructor) 존재
 * 4. equals(), hashCode() 메서드 구현 (Lombok @EqualsAndHashCode)
 */
@NoArgsConstructor // JPA가 사용할 기본 생성자
@AllArgsConstructor // 편의를 위한 전체 필드 생성자
@EqualsAndHashCode // 복합 키의 동일성을 비교하기 위해 필수
public class UserMissionId implements Serializable {

    /**
     * 이 필드명(user)은 반드시 UserMission 엔티티에서 @Id로 매핑된
     * 필드의 이름과 일치해야됨(필수적)

     * User 엔티티의 PK 타입(Long)과 일치
     */
    private Long user;

    /**
     * 이 필드명(mission)은 반드시 UserMission 엔티티에서 @Id로 매핑된
     * 필드의 이름과 일치해야됨

     * 타입 또한, Mission 엔티티의 PK 타입(Long)과 일치
     */
    private Long mission;
}
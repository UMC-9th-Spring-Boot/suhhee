// UserMissionId.java (새로 생성)

package com.example._mission.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable; // 직렬화를 위해 import

/**
 * UserMission 엔티티의 복합 키를 정의하기 위한 "헬퍼 클래스"입니다.
 * @ IdClass로 사용되려면 3가지 조건을 지켜야 합니다.
 * 1. public 클래스여야 합니다.
 * 2. Serializable 인터페이스를 구현(implements)해야 합니다.
 * 3. 기본 생성자(NoArgsConstructor)가 있어야 합니다.
 * 4. equals() 와 hashCode() 메서드를 구현해야 합니다. (Lombok @EqualsAndHashCode)
 */
@NoArgsConstructor // JPA가 사용할 기본 생성자
@AllArgsConstructor // 편의를 위한 전체 필드 생성자
@EqualsAndHashCode // 복합 키의 동일성을 비교하기 위해 필수
public class UserMissionId implements Serializable {

    /**
     * 이 필드명(user)은 반드시 UserMission 엔티티에서 @Id로 매핑된
     * 필드의 이름과 *정확히 일치*해야 합니다.

     * 타입 또한, User 엔티티의 PK 타입(Long)과 일치해야 합니다.
     */
    private Long user;

    /**
     * 이 필드명(mission)은 반드시 UserMission 엔티티에서 @Id로 매핑된
     * 필드의 이름과 *정확히 일치*해야 합니다.

     * 타입 또한, Mission 엔티티의 PK 타입(Long)과 일치해야 합니다.
     */
    private Long mission;
}
// UserTermsAgreementId.java (새로 생성)

package com.example._mission.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserTermsAgreement 엔티티의 복합 키를 정의하기 위한 "헬퍼 클래스"입니다.
 * UserMissionId와 마찬가지로 Serializable, NoArgsConstructor, EqualsAndHashCode가 필요합니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserTermsAgreementId implements Serializable {

    /**
     * UserTermsAgreement 엔티티의 @Id 필드 'user'와 이름이 일치해야 합니다.
     */
    private Long user;

    /**
     * UserTermsAgreement 엔티티의 @Id 필드 'terms'와 이름이 일치해야 합니다.
     */
    private Long terms;
}
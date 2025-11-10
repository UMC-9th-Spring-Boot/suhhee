package com.example._mission.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    // 회원 기본 정보
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 20)
    private String userPw;

    @Column(length = 20)
    private String userPhone;

    /*
    Enum 타입 Gender를 사용
    @Enumerated(EnumType.STRING)
    → Enum 이름 자체를 DB에 문자열로 저장
    (MALE / FEMALE 로 저장됨)
    → ORDINAL(숫자 저장)을 피하는 것은 유지보수성을 높이기 위함
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime userBirth;

    @Column(length = 100)
    private String userEmail;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    /*
    status: 회원의 현재 상태 (ACTIVE, INACTIVE, DELETED 등..)
    inactiveDate: 비활성화(탈퇴)된 날짜
     */
    @Column(length = 10)
    private String status;
    private LocalDateTime inactiveDate;

    private Integer currentPoints;

    // 연관 관계

    @OneToMany(mappedBy = "user")
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserTermsAgreement> userTermsAgreementList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserFoodPreference> userFoodPreferenceList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissionList = new ArrayList<>();

    public enum Gender {
        MALE, FEMALE, NONE
    }
}
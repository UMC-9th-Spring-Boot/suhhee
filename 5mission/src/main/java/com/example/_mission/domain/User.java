package com.example._mission.domain;

import com.example._mission.domain.enums.Role;
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

    @Column(length = 20)
    private String nickname;

    @Column(nullable = false)
    private String userPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 20)
    private String userPhone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime userBirth;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

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
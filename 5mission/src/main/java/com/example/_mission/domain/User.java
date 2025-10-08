package com.example._mission.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 20)
    private String userPw;

    @Column(length = 20)
    private String userPhone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime userBirth;

    @Column(length = 100)
    private String userEmail;

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
}
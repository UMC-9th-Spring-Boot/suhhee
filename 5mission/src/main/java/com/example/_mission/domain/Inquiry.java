package com.example._mission.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry {

    /*
    @Entity
→ 이 클래스가 JPA 엔티티임을 명시 . DB의 테이블과 매핑되는 클래스
→ JPA가 실행될 때 Inquiry라는 이름의 테이블(또는 지정한 이름)을 자동으로 인식하거나 생성
@Getter
→ Lombok이 자동으로 모든 필드의 getter 메서드를 만들어줌
(예: getId(), getInqTitle() 등)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
→ 기본 생성자를 protected로 생성
→ JPA가 프록시 객체를 만들 때 기본 생성자를 필요로 하기 때문에, protected 수준으로 만들어두는 것이 일반적
→ 개발자가 직접 new로 생성하는 걸 막고, 팩토리 메서드나 빌더를 통해 객체를 만들도록 유도할 수 있음
@AllArgsConstructor
→ 모든 필드를 인자로 받는 생성자를 자동으로 생성
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String inqTitle;

    // 대용량 텍스트(LOB: Large Object) 타입을 저장
    //→ MySQL에서는 일반적으로 TEXT 타입으로 매핑
    @Lob
    @Column(nullable = false)
    private String content;

    @Lob
    private String replyContent;

    private LocalDateTime replyDate;
}
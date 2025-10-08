package com.example._mission.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private Local local;

    @Column(nullable = false, length = 50)
    private String storeName;

    @Column(nullable = false, length = 50)
    private String storeCategory;

    @Column(nullable = false, length = 100)
    private String storeAdd;

    @Column(length = 100)
    private String presId;
}
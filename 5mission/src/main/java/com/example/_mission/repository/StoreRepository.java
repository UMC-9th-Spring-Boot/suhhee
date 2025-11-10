package com.example._mission.repository;

import com.example._mission.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

// Store 엔티티를 다루는 JpaRepository
// <엔티티, PK 타입>
public interface StoreRepository extends JpaRepository<Store, Long> {
}
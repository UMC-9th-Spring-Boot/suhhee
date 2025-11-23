package com.example._mission.repository;

import com.example._mission.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

// Store 엔티티를 다루는 JpaRepository
// <엔티티, PK 타입>
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreName(String storeName);
}
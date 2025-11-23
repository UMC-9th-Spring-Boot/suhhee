package com.example._mission.repository;

import com.example._mission.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    JpaRepository는 PagingAndSortingRepository에서 페이징과 소팅 기능을 제공하기에
    // 인터페이스를 extends 하는 것 만으로도 page 관련 메서드를 사용할 수 있음 (9주차 워크북 내용)
}
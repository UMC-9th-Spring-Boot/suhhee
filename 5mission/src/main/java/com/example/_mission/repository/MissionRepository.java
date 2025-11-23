package com.example._mission.repository;

import com.example._mission.domain.Local;
import com.example._mission.domain.Mission;
import com.example._mission.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.local = :local")
    Page<Mission> findMissionsByLocal(@Param("local") Local local, Pageable pageable);
    // 가게로 미션 목록 조회 ..페이징
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}
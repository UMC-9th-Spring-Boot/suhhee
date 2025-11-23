package com.example._mission.service.query;

import com.example._mission.dto.ReviewResDTO;
import org.springframework.data.domain.Page;

public interface ReviewQueryService {
    // 가게의 리뷰 목록 조회
    ReviewResDTO.ReviewPreViewListDTO getReviewList(String storeName, Integer page);
}
package com.example._mission.service.command;

import com.example._mission.dto.ReviewReqDTO;
import com.example._mission.dto.ReviewResDTO;

// 리뷰 변경 관련 서비스의 설계
public interface ReviewCommandService {

    // 가게에 리뷰를 추가
    ReviewResDTO.AddReviewDTO addReview(ReviewReqDTO.AddReviewDTO dto, Long storeId);
}
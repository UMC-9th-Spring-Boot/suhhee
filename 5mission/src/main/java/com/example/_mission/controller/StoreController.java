package com.example._mission.controller;

import com.example._mission.dto.ReviewReqDTO;
import com.example._mission.dto.ReviewResDTO;
import com.example._mission.global.apiPayload.ApiResponse;
import com.example._mission.global.apiPayload.code.GeneralSuccessCode;
import com.example._mission.service.command.ReviewCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores") // 이 컨트롤러의 기본 URL
public class StoreController {

    private final ReviewCommandService reviewCommandService;

    /*
    미션 1: 가게에 리뷰 추가 API
    URL: POST /stores/{store-id}/reviews
     */
    @PostMapping("/{store-id}/reviews")
    public ApiResponse<ReviewResDTO.AddReviewDTO> addReview(
            // URL {store-id}에서 값 가져옴..
            // @PathVariable는 URL 경로에 들어있는 변수를 가져옴
            @PathVariable("store-id") Long storeId,

            // 요청 body에서 DTO 가져옴
            @RequestBody @Valid ReviewReqDTO.AddReviewDTO dto
    ) {

        // 서비스 호출
        ReviewResDTO.AddReviewDTO responseDTO = reviewCommandService.addReview(dto, storeId);

        // 성공 응답 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, responseDTO);
    }
}
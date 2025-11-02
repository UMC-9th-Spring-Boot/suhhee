package com.example._mission.controller;

import com.example._mission.dto.ReviewDto;
import com.example._mission.global.apiPayload.ApiResponse; //ApiResponse import
import com.example._mission.global.apiPayload.code.GeneralSuccessCode; // 성공 코드 import
import com.example._mission.service.ReviewService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 반환 타입을 Page<ReviewDto>에서 ApiResponse<Page<ReviewDto>>로 변경
    @GetMapping("/my")
    public ApiResponse<Page<ReviewDto>> getMyReviews(
            @RequestParam(name = "store", required = false) String storeName,
            @RequestParam(name = "star", required = false) Integer starRating,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Long tempUserId = 1L; // (임시 ID)

        // Service를 호출하여 Page<ReviewDto> 객체를 받음
        // 만약 Service에서 유저를 못찾으면, 이 단계에서 GeneralException이 발생하고
        // GeneralExceptionAdvice가 자동으로 '실패' 응답을 처리함
        Page<ReviewDto> reviewPage = reviewService.getMyReviews(tempUserId, storeName, starRating, pageable);

        // Service가 성공적으로 데이터를 반환한 경우
        // ApiResponse.onSuccess()로 '성공' 응답 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewPage);
    }
}
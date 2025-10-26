// ReviewController.java

package com.example._mission.controller;

import com.example._mission.dto.ReviewDto;
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

    // 반환 타입 Page<ReviewDto>
    @GetMapping("/my")
    public Page<ReviewDto> getMyReviews(
            @RequestParam(name = "store", required = false) String storeName,
            @RequestParam(name = "star", required = false) Integer starRating,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Long tempUserId = 1L;

        // 반환 타입이 Page<ReviewDto>로 자동 변경
        return reviewService.getMyReviews(tempUserId, storeName, starRating, pageable);
    }
}
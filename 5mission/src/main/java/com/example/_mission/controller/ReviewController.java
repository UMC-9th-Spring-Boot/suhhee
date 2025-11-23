package com.example._mission.controller;

import com.example._mission.dto.ReviewDto;
import com.example._mission.dto.ReviewResDTO;
import com.example._mission.global.apiPayload.ApiResponse;
import com.example._mission.global.apiPayload.code.GeneralSuccessCode;
import com.example._mission.global.apiPayload.code.ReviewSuccessCode;
import com.example._mission.service.ReviewService;
import com.example._mission.service.query.ReviewQueryService;
import com.example._mission.global.annotation.CheckPage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Validated //  @CheckPage 이 동작됨
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    // 가게의 리뷰 목록 조회 API
    @GetMapping("")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", // API 제목
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다. ") // 상세설명
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(mediaType = "application/json")),
    })
    @Parameters({
            @Parameter(name = "storeName", description = "가게의 이름"),
            @Parameter(name = "page", description = "페이지 번호"),
    })
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviewList(
            @RequestParam(name = "storeName") String storeName,
            @RequestParam(name = "page") Integer page
    ){
        ReviewResDTO.ReviewPreViewListDTO result = reviewQueryService.getReviewList(storeName, page);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_FETCHED, result);
    }

    // 나의 리뷰 목록 조회 API
    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회합니다. 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            // ... 에러 응답들 ...
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (첫 페이지: 1)"),
            @Parameter(name = "store", description = "가게 이름 (선택)"),
            @Parameter(name = "star", description = "별점 (선택)")
    })
    public ApiResponse<Page<ReviewDto>> getMyReviews(
            @RequestParam(name = "store", required = false) String storeName,
            @RequestParam(name = "star", required = false) Integer starRating,

            //@CheckPage 적용.. Integer page로 받음
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        // 페이징 객체 생성 (0부터 시작하므로 page - 1)
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<ReviewDto> reviewPage = reviewService.getMyReviews(1L, storeName, starRating, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewPage);
    }
}
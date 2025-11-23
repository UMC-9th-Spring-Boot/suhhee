package com.example._mission.controller;

import com.example._mission.dto.MissionResDTO;
import com.example._mission.global.annotation.CheckPage;
import com.example._mission.global.apiPayload.ApiResponse;
import com.example._mission.global.apiPayload.code.GeneralSuccessCode;
import com.example._mission.service.command.MissionCommandService;
import com.example._mission.service.query.MissionQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    /*
    미션 2: 미션 도전하기 API
    URL: POST /missions/{mission-id}/challenge
     */
    @PostMapping("/{mission-id}/challenge")
    public ApiResponse<MissionResDTO.ChallengeMissionDTO> challengeMission(
            @PathVariable("mission-id") Long missionId
    ) {
        // 서비스 호출
        MissionResDTO.ChallengeMissionDTO responseDTO = missionCommandService.challengeMission(missionId);

        // 성공 응답 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, responseDTO);
    }

    // 특정 가게의 미션 목록 조회 API
    // URL: GET /missions/stores/{storeId}
    @GetMapping("/stores/{storeId}")
    @Operation(summary = "가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(mediaType = "application/json")),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디"),
            @Parameter(name = "page", description = "페이지 번호 (첫 페이지: 1)"),
    })
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page // 검증 적용!
    ) {
        // page - 1 로 변환하여 서비스에 전달
        MissionResDTO.MissionPreViewListDTO result = missionQueryService.getMissionList(storeId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 진행 중인 미션 목록 조회 API
    // URL: GET /missions/my
    @GetMapping("/my")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션 목록을 조회합니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(mediaType = "application/json")),
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (첫 페이지: 1)"),
    })
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyOngoingMissions(
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        Long userId = 1L; // 임시 1번 유저
        MissionResDTO.MyMissionListDTO result = missionQueryService.getMyOngoingMissions(userId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
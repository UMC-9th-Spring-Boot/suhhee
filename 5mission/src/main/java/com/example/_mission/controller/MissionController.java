package com.example._mission.controller;

import com.example._mission.dto.MissionResDTO;
import com.example._mission.global.apiPayload.ApiResponse;
import com.example._mission.global.apiPayload.code.GeneralSuccessCode;
import com.example._mission.service.command.MissionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

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
}
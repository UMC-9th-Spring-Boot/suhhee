package com.example._mission.controller;

import com.example._mission.dto.UserReqDTO;
import com.example._mission.dto.UserResDTO;
import com.example._mission.global.apiPayload.ApiResponse;
import com.example._mission.global.apiPayload.code.MemberSuccessCode;
import com.example._mission.service.command.UserCommandService;

import com.example._mission.service.query.UserQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    // 주입 타입
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(

            // @RequestBody 옆에 @Valid를 붙임.. 왜?..
            // -> 이 DTO는 Validation(검증)이 필요함. 이라는 뜻
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, userCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, userQueryService.login(dto));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        return ApiResponse.onSuccess(MemberSuccessCode.LOGOUT_SUCCESS, null);
    }
}
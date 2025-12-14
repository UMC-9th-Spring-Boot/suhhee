package com.example._mission.global.security;

import com.example._mission.global.apiPayload.ApiResponse;
import com.example._mission.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        // 응답 타입을 JSON으로 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 만들어 뒀었던 ApiResponse 형식으로 에러 메시지 생성
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.UNAUTHORIZED,
                null
        );

        // JSON으로 변환하여 응답 보냄 ..
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
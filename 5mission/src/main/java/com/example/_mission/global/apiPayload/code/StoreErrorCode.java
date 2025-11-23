package com.example._mission.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    // 가게를 찾을 수 없을 때 발생하는 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "해당 가게를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
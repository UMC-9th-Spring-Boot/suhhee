package com.example._mission.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * FoodCategory 관련 에러 코드를 정의하는 Enum
 * BaseErrorCode 인터페이스를 구현
 */
@Getter
@AllArgsConstructor
public enum FoodCategoryErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "해당 음식 카테고리를 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
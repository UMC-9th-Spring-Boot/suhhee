package com.example._mission.global.apiPayload.exception;

import com.example._mission.global.apiPayload.code.BaseErrorCode;

/**
 * FoodCategory 관련 예외 처리를 위한 클래스
 * GeneralException을 상속받음
 */
public class FoodCategoryException extends GeneralException {
    public FoodCategoryException(BaseErrorCode code) {
        super(code);
    }
}
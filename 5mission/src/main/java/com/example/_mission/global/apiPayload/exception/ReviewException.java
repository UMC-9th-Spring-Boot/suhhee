package com.example._mission.global.apiPayload.exception;

import com.example._mission.global.apiPayload.code.BaseErrorCode;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
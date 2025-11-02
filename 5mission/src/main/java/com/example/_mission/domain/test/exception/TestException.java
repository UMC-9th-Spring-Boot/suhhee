package com.example._mission.domain.test.exception;

import com.example._mission.global.apiPayload.code.BaseErrorCode;
import com.example._mission.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}


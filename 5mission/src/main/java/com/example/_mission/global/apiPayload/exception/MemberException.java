package com.example._mission.global.apiPayload.exception;

import com.example._mission.global.apiPayload.code.BaseErrorCode;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
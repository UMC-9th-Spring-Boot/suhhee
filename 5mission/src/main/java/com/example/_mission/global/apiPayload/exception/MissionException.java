package com.example._mission.global.apiPayload.exception;

import com.example._mission.global.apiPayload.code.BaseErrorCode;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
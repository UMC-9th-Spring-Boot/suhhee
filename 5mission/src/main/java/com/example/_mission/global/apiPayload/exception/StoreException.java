package com.example._mission.global.apiPayload.exception;

import com.example._mission.global.apiPayload.code.BaseErrorCode;
import com.example._mission.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}

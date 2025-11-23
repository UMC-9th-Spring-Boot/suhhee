package com.example._mission.global.validator;

import com.example._mission.global.annotation.CheckPage;
import com.example._mission.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 값이 없거나 1보다 작으면 에러 처리
        if (value == null || value < 1) {
            // 기본 에러 메시지 끄기
            context.disableDefaultConstraintViolation();

            // 커스텀 에러 코드의 메시지를 사용
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.PAGE_NOT_VALID.toString())
                    .addConstraintViolation();

            return false; // 검증 실패
        }
        return true; // 검증 성공
    }
}
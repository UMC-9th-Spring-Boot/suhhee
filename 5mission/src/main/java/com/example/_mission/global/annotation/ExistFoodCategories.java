package com.example._mission.global.annotation;

import com.example._mission.global.validator.FoodCategoryExistValidator; // 만들 Validator
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Constraint: 이 어노테이션이 검증 로직을 가짐을 명시
 * - validatedBy: 'FoodCategoryExistValidator' 클래스가 실제 검증을 수행
 */
@Documented
@Constraint(validatedBy = FoodCategoryExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoodCategories {

    // 검증 실패 시 기본으로 반환할 메시지
    String message() default "해당 음식 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
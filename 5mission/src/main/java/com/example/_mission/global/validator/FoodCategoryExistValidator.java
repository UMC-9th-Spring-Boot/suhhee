package com.example._mission.global.validator;

import com.example._mission.global.annotation.ExistFoodCategories;
import com.example._mission.global.apiPayload.code.FoodCategoryErrorCode; // 내가 작성한 에러 코드
import com.example._mission.repository.FoodCategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

//@ExistFoodCategories 어노테이션의 실제 검증 로직을 구현하는 클래스
@Component
@RequiredArgsConstructor
// ConstraintValidator 인터페이스 구현
//  <검증 어노테이션, 검증할 대상의 타입>
public class FoodCategoryExistValidator implements ConstraintValidator<ExistFoodCategories, List<Long>> {

    private final FoodCategoryRepository foodCategoryRepository; // DB 조회를 위해 Repository 주입

    @Override
    public void initialize(ExistFoodCategories constraintAnnotation) {
        // 어노테이션의 파라미터를 가져와서 초기화할 때 사용
    }

    /**
     * 실제 검증 로직이 담긴 메서드
     * @param values @ExistFoodCategories가 붙은 필드의 값 (즉, List<Long> preferCategory)
     * @return 유효하면 true 아니면 false
     */
    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        // Stream을 사용한 검증
        // values(ID 목록)의 모든 ID가 DB에 존재하는지 확인
        boolean isValid = values.stream()
                .allMatch(value -> foodCategoryRepository.existsById(value));

        // 5. 만약 하나라도 존재하지 않는다면 (isValid가 false라면) [cite: 695]
        if (!isValid) {
            // 6. 기본 메시지 대신, 우리가 FoodCategoryErrorCode에 정의한 메시지를 사용하도록 설정 [cite: 698-700]
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodCategoryErrorCode.NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        return isValid; //
    }
}
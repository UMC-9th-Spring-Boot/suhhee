package com.example._mission.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 전역(General)으로 사용되는 성공 코드들을 모아둔 Enum
 * BaseSuccessCode 인터페이스를 구현
 * * @Getter : 각 필드(code, message)에 대한 'Getter' 메서드를 자동으로 생성
 * @ AllArgsConstructor : 모든 필드를 인자로 받는 '생성자'를 자동으로 생성
 */
@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    /**
     * 200 OK : 요청이 성공적으로 처리되었음을 나타냄
     * 가장 일반적인 성공 응답 코드
     */
    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "COMMON201","성공적으로 생성되었습니다."),
    ;

    // Enum의 필드
    private final HttpStatus status;
    private final String code;
    private final String message;


    // BaseSuccessCode 인터페이스 메서드 구현
    // @Getter가 자동으로 생성해 주지만.. 명시적으로 보여주기 위해 작성할 수 있음

    /**
     * BaseSuccessCode 인터페이스의 getCode() 메서드 구현
     * @return 정의된 성공 코드 (e.g., "COMMON200")
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * BaseSuccessCode 인터페이스의 getMessage() 메서드 구현
     * @return 정의된 성공 메시지 (e.g., "성공적으로 요청을 처리했습니다.")
     */
    @Override
    public String getMessage() {
        return message;
    }
}
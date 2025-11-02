package com.example._mission.global.apiPayload.code;

/**
 * 성공 코드에 대한 인터페이스
 * 이 인터페이스를 구현하는 모든 Enum은
 * getCode()와 getMessage() 메서드를 반드시 구현해야됨 !!
 * * - getCode() : 성공 코드를 문자열로 반환 (e.g., "COMMON200")
 * - getMessage() : 성공 메시지를 문자열로 반환 (e.g., "성공적으로 처리되었습니다.")
 */
public interface BaseSuccessCode {

    /**
     * 시스템에서 정의한 "성공 코드"를 반환
     * @return String 형식의 성공 코드
     */
    String getCode();

    /**
     * 성공에 대한 "상세 메시지"를 반환
     * @return String 형식의 성공 메시지
     */
    String getMessage();
}
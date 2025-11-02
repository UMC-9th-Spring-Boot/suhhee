package com.example._mission.global.apiPayload;

import com.example._mission.global.apiPayload.code.BaseErrorCode;
import com.example._mission.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// @JsonPropertyOrder : JSON으로 변환될 때 명시된 순서대로 필드들이 정렬됨
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    /**
     @JsonInclude(JsonInclude.Include.NON_NULL)
     이 어노테이션은 'result' 필드가 null일 경우
     JSON 응답 본문에서 'result' 키 자체를 생략하도록 만듦
     (이걸 사용하지 않으면 "result": null 이라고 표시됨)
     성공했지만(e.g., DELETE), 딱히 반환할 데이터가 없을 때 유용
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result")
    private T result;


    // 성공
    /**
     * 성공 응답을 생성할 때 사용하는 정적 메서드
     * @param code BaseSuccessCode (e.g., GeneralSuccessCode.OK)
     * @param result 응답으로 보낼 데이터 (DTO 등)
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(
                true,             // isSuccess = true
                code.getCode(),   // code (e.g., "COMMON200")
                code.getMessage(),// message (e.g., "성공적으로 요청을 처리했습니다.")
                result            // result 데이터
        );
    }


    // 실패
    /**
     * 실패 응답을 생성할 때 사용하는 정적 메서드
     * (e.g., Validation Error 시 어떤 필드가 잘못되었는지 상세 데이터를 result에 담아 보낼 때 사용)
     * @param code BaseErrorCode (e.g., GeneralErrorCode.BAD_REQUEST)
     * @param result 응답으로 보낼 데이터 (e.g., Error DTO)
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,            // isSuccess = false
                code.getCode(),   // code (e.g., "COMMON400_1")
                code.getMessage(),// message (e.g., "잘못된 요청입니다.")
                result            // result 데이터 (e.g., 어느 필드가 틀렸는지)
        );
    }
}
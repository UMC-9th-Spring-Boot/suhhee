package com.example._mission.service.command;

import com.example._mission.dto.UserReqDTO;
import com.example._mission.dto.UserResDTO;

//회원 관련 변경(Command) 작업을 정의

public interface UserCommandService {

    // sign-up 메서드의 시그니처인 이름과 파라미터만 가져옴
    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
package com.example._mission.service.query;

import com.example._mission.dto.UserReqDTO;
import com.example._mission.dto.UserResDTO;

public interface UserQueryService {
    // 로그인
    UserResDTO.LoginDTO login(UserReqDTO.LoginDTO request);
}
package com.example._mission.service.query;

import com.example._mission.converter.UserConverter;
import com.example._mission.domain.User;
import com.example._mission.dto.UserReqDTO;
import com.example._mission.dto.UserResDTO;
import com.example._mission.global.apiPayload.code.MemberErrorCode;
import com.example._mission.global.apiPayload.exception.MemberException;
import com.example._mission.global.security.CustomUserDetails;
import com.example._mission.global.security.JwtUtil;
import com.example._mission.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public UserResDTO.LoginDTO login(
            UserReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), user.getUserPw())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return UserConverter.toLoginDTO(user, accessToken);
    }
}
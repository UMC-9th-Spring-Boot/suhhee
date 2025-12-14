package com.example._mission.service;

import com.example._mission.domain.User;
import com.example._mission.global.apiPayload.code.MemberErrorCode;
import com.example._mission.global.apiPayload.exception.MemberException;
import com.example._mission.global.security.CustomUserDetails;
import com.example._mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

/*
워크북10 :
CustomUserDetailService : loadByUsername 메소드로 AuthenticationManager의 호출을 받아
DB에서 findByEmail 메소드로 사용자 정보를 조회
조회된 user 엔티티를 기반으로 Spring Security가 요구하는 UserDetails 객체 반환 ..
 */
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 워크북10 : 검증할 user 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 워크북10 : CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}
package com.example._mission.global.security;

import com.example._mission.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    // 워크북10 : getAuthorities : 권한을 List 형태로 반환 (ADMIN or USER)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().toString());
    }

    // 워크북10 : getPassword : 비밀번호를 반환
    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    // 워크북10 : getUsername : 아이디를 반환 (여기선 이메일을 아이디로 사용!)
    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
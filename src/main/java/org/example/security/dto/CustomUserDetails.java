package org.example.security.dto;

import org.example.security.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한을 가져오는 메소드
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 계정이 만료되었는지

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // 계정이 잠겨있는지

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // 비밀번호가 만료되지 않았는지

    @Override
    public boolean isEnabled() {
        return true;
    } // 계정이 사용가능한지
}

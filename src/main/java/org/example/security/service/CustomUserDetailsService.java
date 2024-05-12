package org.example.security.service;

import lombok.RequiredArgsConstructor;
import org.example.security.dto.CustomUserDetails;
import org.example.security.entity.UserEntity;
import org.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username); // DB에서 회원의 정보를 가지고 온다.

        if(userEntity != null){
            return new CustomUserDetails(userEntity);
        }

        return null;

    }
}

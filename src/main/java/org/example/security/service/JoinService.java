package org.example.security.service;

import lombok.RequiredArgsConstructor;
import org.example.security.dto.JoinDTO;
import org.example.security.entity.UserEntity;
import org.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

  /* public JoinService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository){
       this.bCryptPasswordEncoder = bCryptPasswordEncoder;
       this.userRepository = userRepository;
   }*/

    public void joinProcess(JoinDTO joinDTO){

        boolean hasName = userRepository.existsByUsername(joinDTO.getUsername());
        System.out.println("hasName : " + hasName);
        if(!hasName){
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(joinDTO.getUsername());
            userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);
        }else{
            System.out.println("유저네임 중복");
        }


    }
}

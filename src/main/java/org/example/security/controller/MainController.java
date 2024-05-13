package org.example.security.controller;

import org.example.security.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainP(Model model) {

//        UserEntity authentication = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("UserEntity : " + authentication); // 오류 -> getPrincipal()로 반환되는 값은 org.springframework.security.core.userdetails.User 클래스의 인스턴스

        // 이름
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        // 권한
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // main 페이지에 변수 담기
        model.addAttribute("id" , id);
        model.addAttribute("role",role);
        return "main";
    }
}

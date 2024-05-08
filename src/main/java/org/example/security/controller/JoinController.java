package org.example.security.controller;

import lombok.RequiredArgsConstructor;
import org.example.security.dto.JoinDTO;
import org.example.security.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    private final JoinService joinService;

    // 생성자 주입
    @Autowired
    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }


    @GetMapping("/join")
    public String joinP(){
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(JoinDTO joinDTO){
        System.out.println(joinDTO.getUsername());

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}

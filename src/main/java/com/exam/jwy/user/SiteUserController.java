package com.exam.jwy.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SiteUserController {
    private final SiteUserService userService;

    @GetMapping("/joinForm")
    public String join(JoinForm joinForm){
        return "joinForm";
    }

    @PostMapping("/join")
    public String doJoin(@Valid JoinForm joinForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "joinForm";
        }
        SiteUser user = userService.create(joinForm);
        return "/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

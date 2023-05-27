package com.exam.jwy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SiteUserController {
    private final SiteUserService userService;

    @GetMapping("/joinForm")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String doJoin(JoinForm joinForm){
        SiteUser user = userService.create(joinForm);
        return "/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

package com.exam.jwy.user;

import com.exam.jwy.Form.JoinForm;
import com.exam.jwy.Form.LoginForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String doJoin(Model model, @Valid JoinForm joinForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "joinForm";
        }
        SiteUser user = userService.create(joinForm);
        model.addAttribute("user", user);
        return "redirect:/question/list";
    }
    @GetMapping("/loginForm")
    public String login(LoginForm loginForm){
        return "loginForm";
    }
    @GetMapping("/loginError")
    public String loginerror(){
        return "loginError";
    }
}

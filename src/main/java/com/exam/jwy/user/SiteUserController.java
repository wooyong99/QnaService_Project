package com.exam.jwy.user;

import com.exam.jwy.Exception.SignUpEmailException;
import com.exam.jwy.Exception.SignUpUserNameException;
import com.exam.jwy.Form.JoinForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String doJoin(@Valid JoinForm joinForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "joinForm";
        }
        if(!joinForm.getPassword().equals(joinForm.getConfirm_password())){
            bindingResult.rejectValue("confirm_password", "passwordIncorrect","2개의 패스워드가 일치하지 않습니다." );
            return "joinForm";
        }
        try{
            SiteUser user = userService.create(joinForm);
        }catch(SignUpUserNameException e){
            bindingResult.rejectValue("username","usernameDuplicate", "이미 존재하는 username입니다.");
            return "joinForm";
        }catch(SignUpEmailException e){
            bindingResult.rejectValue("email","emailDuplicate", "이미 존재하는 Email입니다.");
            return "joinForm";
        }
        return "redirect:/question/list";
    }
    @GetMapping("/loginForm")
    public String login(){
        return "loginForm";
    }
    @PostMapping("/loginError")
    public String loginerror(Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("errorMessage", request.getAttribute("errorMessage"));
        return "loginForm";
    }
}

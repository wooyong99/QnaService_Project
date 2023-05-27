package com.exam.jwy.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginForm {
    @NotBlank(message="Username을 입력해주세요.")
    String username;
    @NotBlank(message="Password를 입력해주세요.")
    String password;
}

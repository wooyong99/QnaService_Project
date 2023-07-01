package com.exam.jwy.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinForm {
    @NotBlank(message = "ID를 입력해주세요.")
    public String username;
    @NotBlank(message = "PASSWORD를 입력해주세요.")
    public String password;
    @NotBlank(message = "동일한 PASSWORD를 입력해주세요.")
    public String confirm_password;
    @NotBlank(message = "Email을 입력해주세요.")
    @Email(message = "Email형식으로 입력해주세요.")
    public String email;
}

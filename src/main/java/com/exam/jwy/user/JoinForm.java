package com.exam.jwy.user;

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
}

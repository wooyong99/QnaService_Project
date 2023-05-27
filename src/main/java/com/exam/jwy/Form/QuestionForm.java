package com.exam.jwy.Form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionForm {
    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(max=200, message = "제목은 200자 이내입니다.")
    public String subject;
    @NotBlank(message = "내용은 필수 항목입니다.")
    public String content;
}

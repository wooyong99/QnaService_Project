package com.exam.jwy.Answer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerForm {
    @NotBlank(message = "답변을 입력해주세요.")
    public String content;
}

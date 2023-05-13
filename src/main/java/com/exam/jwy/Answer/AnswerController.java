package com.exam.jwy.Answer;

import com.exam.jwy.Question.Question;
import com.exam.jwy.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createArticle(Model model , @PathVariable("id") int id , String content){
        Question question = questionService.getQuestionById(id);
        answerService.createAnswer(question, content);

        List<Answer> answerList = answerService.getAnswerList(question.getId());

        model.addAttribute("answerList", answerList);
        model.addAttribute("question", question);
        return "redirect:/question/detail/"+id;
    }
}

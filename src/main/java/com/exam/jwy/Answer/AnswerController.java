package com.exam.jwy.Answer;

import com.exam.jwy.Form.AnswerForm;
import com.exam.jwy.Question.Question;
import com.exam.jwy.Question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createArticle(Model model , @PathVariable("id") int id , @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question question = questionService.getQuestionById(id);
        List<Answer> answerList = answerService.getAnswerList(question.getId());

        if(bindingResult.hasErrors()){
            model.addAttribute("answerList", answerList);
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.createAnswer(question, answerForm.getContent());


        model.addAttribute("answerList", answerList);
        model.addAttribute("question", question);
        return "redirect:/question/detail/"+id;
    }
    @GetMapping("/delete/{question_id}/{id}")
    public String deleteAnswer(@PathVariable("question_id") Integer q_id, @PathVariable("id") Integer id){
        answerService.deleteAnswer(id);
        return "redirect:/question/detail/%d".formatted(q_id);
    }
}

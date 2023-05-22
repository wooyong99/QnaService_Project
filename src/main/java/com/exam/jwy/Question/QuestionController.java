package com.exam.jwy.Question;

import com.exam.jwy.Answer.Answer;
import com.exam.jwy.Answer.AnswerForm;
import com.exam.jwy.Answer.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final 붙은 변수는 생성자 주입을 해준다.
@Controller
public class QuestionController {
  private final QuestionService questionService;
  private final AnswerService answerService;
  @GetMapping("/")
  public String root(){
    return "redirect:/question/list";
  }
  @GetMapping(value = {"/question/list","/question/list/{id}","/question/list/{id}/{order_by}"})
  public String list(Model model, @PathVariable Optional<Integer> id, @PathVariable Optional<String> order_by){
    Page<Question> pagingList = questionService.getPageList(id.isPresent() ? id.get()-1 : 0);
    String order = (order_by.isEmpty() ? "ASC" : order_by.get());
    if(order.equals("DESC")){
      pagingList = questionService.getPageListDesc(id.isPresent() ? id.get()-1 : 0);
    }
    model.addAttribute("pagingNum", (pagingList.getNumber() /5 ) * 5 + 1 );
    model.addAttribute("pagingList", pagingList);
    return "question_list"; // ResponseBody 어노테이션을 제거하면 templates 파일에 있는 파일을 뷰로 삼는다.
  }

  @GetMapping("/question/detail/{id}")
  public String detail(Model model, @PathVariable int id, AnswerForm answerForm){
    Question question = questionService.getQuestionById(id);
    List<Answer> answerList = answerService.getAnswerList(question.getId());

    model.addAttribute("answerList", answerList);
    model.addAttribute("question", question);
    return "question_detail";
    }
    @GetMapping("/question/create")
    public String create(QuestionForm questionForm){
      return "question_create";
    }
    @PostMapping("/question/doCreate")
    public String doCreate(Model model, @Valid QuestionForm questionForm, BindingResult bindingResult){
      if(bindingResult.hasErrors()){
        return "question_create";
      }
      questionService.create(questionForm.subject, questionForm.content);
      return "redirect:/question/list";
    }
    @GetMapping("/question/modify/{id}")
    public String modify(Model model, @PathVariable int id, QuestionForm questionForm){
      Question question = questionService.getQuestionById(id);
      model.addAttribute("question", question);

      return "question_modify";
    }
    @PostMapping("/question/doModify/{id}")
    public String doModify(Model model, @PathVariable int id, @Valid QuestionForm questionForm, BindingResult bindingResult){
      Question question = questionService.getQuestionById(id);
      if(bindingResult.hasErrors()){
        model.addAttribute("question_id", question.getId());
        return "question_modify";
      }
      questionService.update(question, questionForm.subject, questionForm.content);
      return "redirect:/question/detail/%d".formatted(id);
    }
}

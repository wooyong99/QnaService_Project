package com.exam.jwy.Question;

import com.exam.jwy.Answer.Answer;
import com.exam.jwy.Answer.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor // final 붙은 변수는 생성자 주입을 해준다.
@Controller
public class QuestionController {
  private final QuestionService questionService;
  private final AnswerService answerService;
  @GetMapping("/")
  public String root(){
    return "redirect:/question/list";
  }
  @GetMapping("/question/list")
  public String list(Model model){
    List<Question> questionList = questionService.getList();
    model.addAttribute("questionList", questionList);
    return "question_list"; // ResponseBody 어노테이션을 제거하면 templates 파일에 있는 파일을 뷰로 삼는다.
  }

  @GetMapping("/question/detail/{id}")
  public String detail(Model model, @PathVariable int id){
    Question question = questionService.getQuestionById(id);
    List<Answer> answerList = answerService.getAnswerList(question.getId());

    model.addAttribute("answerList", answerList);
    model.addAttribute("question", question);
    return "question_detail";
    }
    @GetMapping("/question/create")
    public String create(){
      return "question_create";
    }
    @PostMapping("/question/doCreate")
    public String doCreate(Model model, QuestionForm questionForm){
      if(questionForm.getSubject().isEmpty() || questionForm.getSubject().trim().length() == 0){
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("error_msg", "제목을 입력해주세요.");
        return "question_create";
      }
      if(questionForm.getContent().isEmpty() || questionForm.getContent().trim().length() == 0){
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("error_msg", "내용을 입력해주세요.");
        return "question_create";
      }
      questionService.create(questionForm.subject, questionForm.content);
      return "redirect:/question/list";
    }
    @GetMapping("/question/modify/{id}")
    public String modify(Model model, @PathVariable int id){
      Question question = questionService.getQuestionById(id);
      model.addAttribute("question", question);

      return "question_modify";
    }
    @PostMapping("/question/doModify/{id}")
    public String doModify(Model model,@PathVariable int id, QuestionForm questionForm){
      Question question = questionService.getQuestionById(id);
      if(questionForm.getSubject().isEmpty() || questionForm.getSubject().trim().length() == 0){
        model.addAttribute("error_msg", "제목을 입력해주세요.");
        model.addAttribute("question", question);
        return "question_modify";
      }
      if(questionForm.getContent().isEmpty() || questionForm.getContent().trim().length() == 0){
        model.addAttribute("error_msg", "내용을 입력해주세요.");
        model.addAttribute("question", question);
        return "question_modify";
      }
      questionService.update(question, questionForm.subject, questionForm.content);
      return "redirect:/question/detail/%d".formatted(id);
    }
}

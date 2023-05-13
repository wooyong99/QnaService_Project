package com.exam.jwy.Question;

import com.exam.jwy.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public List getList(){
    return questionRepository.findAll();
  }
  public Question getQuestionById(int id){
    return questionRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("no %d question not found".formatted(id)));
  }

  public void create(String subject, String content) {
    Question question = new Question();
    question.setCreateDate(LocalDateTime.now());
    question.setContent(content);
    question.setSubject(subject);

    questionRepository.save(question);
  }
}

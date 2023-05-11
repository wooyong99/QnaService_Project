package com.exam.jwy.Question;

import com.exam.jwy.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    Optional<Question> oq = questionRepository.findById(id);
    if(oq.isPresent()){
      return oq.get();
    }
    throw new DataNotFoundException("question not found"); // oq객체가 null값이라면 DataNotFoundException 예외를 던져라
  }
}

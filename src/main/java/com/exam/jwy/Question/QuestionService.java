package com.exam.jwy.Question;

import com.exam.jwy.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

  public void update(Question question, String subject, String content) {
    question.setSubject(subject);
    question.setContent(content);
    questionRepository.save(question);
  }

  public Page<Question> getPageList(Integer current_page, String order) {
    Page<Question> page_q = null;
    List<Sort.Order> sort = new ArrayList<>();
    if(order.equals("DESC")){
      sort.add(Sort.Order.desc("createDate"));
      sort.add(Sort.Order.desc("id"));
      page_q = questionRepository.findAll(PageRequest.of(current_page, 10, Sort.by(sort)));
    }else{
      page_q = questionRepository.findAll(Pageable.ofSize(10).withPage(current_page));
    }
    return page_q;
  }
  public Page<Question> getPageListSubject(Integer current_page, String sortType){
    List<Sort.Order> sort = new ArrayList<>();
    Page<Question> page_q = null;
    if(sortType.equals("DESC")){
      sort.add(Sort.Order.desc("subject"));
      sort.add(Sort.Order.desc("id"));
      page_q = questionRepository.findAll(PageRequest.of(current_page, 10, Sort.by(sort)));
    }else{
      sort.add(Sort.Order.asc("subject"));
      sort.add(Sort.Order.asc("id"));
      page_q = questionRepository.findAll(PageRequest.of(current_page, 10, Sort.by(sort)));
    }

    return page_q;
  }
}

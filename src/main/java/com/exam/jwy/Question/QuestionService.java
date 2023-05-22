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

  public Page<Question> getPageList(Integer current_page) {
    Page<Question> page_q = questionRepository.findAll(Pageable.ofSize(10).withPage(current_page));
    return page_q;
  }
  public Page<Question> getPageListCreateDateDesc(Integer current_page){
    List<Sort.Order> sort = new ArrayList<>();
    sort.add(Sort.Order.desc("createDate"));
    sort.add(Sort.Order.desc("id"));
    Page<Question> page_q = questionRepository.findAll(PageRequest.of(current_page,10, Sort.by(sort)));
    return page_q;
  }
  public Page<Question> getPageListSubjectDesc(Integer current_page){
    List<Sort.Order> sort = new ArrayList<>();
    sort.add(Sort.Order.desc("subject"));
    sort.add(Sort.Order.desc("id"));
    Page<Question> page_q = questionRepository.findAll(PageRequest.of(current_page, 10, Sort.by(sort)));
    return page_q;
  }
}

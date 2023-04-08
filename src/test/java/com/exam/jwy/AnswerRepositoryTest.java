package com.exam.jwy;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AnswerRepositoryTest {
  @Autowired
  QuestionRepository questionRepository;
  @Autowired
  AnswerRepository answerRepository;

  @BeforeEach
  void beforeTest(){
    clearData();
    createData();
  }

  private void clearData() {
    QuestionRepositoryTest.clearData(questionRepository);

    answerRepository.deleteAll();
    answerRepository.truncateTable();
  }

  void createData(){
    QuestionRepositoryTest.createData(questionRepository);

    Question q1 = questionRepository.findById(1).get();

    Answer a1 = new Answer();
    a1.setContent("1번질문내용");
    a1.setCreateDate(LocalDateTime.now());
    q1.addAnswer(a1);
//    answerRepository.save(a1);

    Question q2 = questionRepository.findById(2).get();
    Answer a2 = new Answer();
    a2.setContent("2번 질문내용");
    a2.setCreateDate(LocalDateTime.now());
    q1.addAnswer(a2);
    questionRepository.save(q1);
//    answerRepository.save(a2);
  }
  @Test
  @Transactional
  @Rollback(false)
  void test1(){
    Answer a = answerRepository.findByContent("1번질문내용");
    assertThat(a.getId()).isEqualTo(1);
  }
  @Transactional
  @Test
  @Rollback(false)
  void test2(){
    Question q = questionRepository.findById(1).get();
    List<Answer> alist = q.getAnswerList();
    alist.stream()
        .map(a -> a.getContent())
        .forEach(System.out::println);
  }
}

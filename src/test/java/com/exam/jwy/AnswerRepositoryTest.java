package com.exam.jwy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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

    answerRepository.truncateTable();
  }

  void createData(){
    QuestionRepositoryTest.createData(questionRepository);

    Question q1 = questionRepository.findById(1).get();
    Answer a1 = new Answer();
    a1.setContent("1번질문내용");
    a1.setCreateDate(LocalDateTime.now());
    a1.setQuestion(q1);

    answerRepository.save(a1);

    Question q2 = questionRepository.findById(2).get();
    Answer a2 = new Answer();
    a2.setContent("2번 질문내용");
    a2.setCreateDate(LocalDateTime.now());
    a2.setQuestion(q2);

    answerRepository.save(a2);
  }
  @Test
  void test1(){
    Answer a = answerRepository.findByContent("1번질문내용");
    assertThat(a.getId()).isEqualTo(1);
  }
}

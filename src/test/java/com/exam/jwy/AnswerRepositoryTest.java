package com.exam.jwy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AnswerRepositoryTest {
  @Autowired
  QuestionRepository questionRepository;
  @Autowired
  AnswerRepository answerRepository;

  @BeforeEach
  void beforeTest(){
    clearData();
  }

  private void clearData() {
    answerRepository.disableForeignKeyChecks();
    answerRepository.truncate();
    answerRepository.enableForeignKeyChecks();
  }

  @Test
  void test0(){
    Question q1 = questionRepository.findById(3).get();
    Answer a1 = new Answer();
    a1.setContent("2번질문내용");
    a1.setCreateDate(LocalDateTime.now());
    a1.setQuestion(q1);

    answerRepository.save(a1);

    Question q2 = questionRepository.findById(2).get();
    Answer a2 = new Answer();
    a2.setContent("질문내용");
    a2.setCreateDate(LocalDateTime.now());
    a2.setQuestion(q2);

    answerRepository.save(a2);
  }
  @Test
  void test1(){
    answerRepository.disableForeignKeyChecks();
    answerRepository.truncate();
    answerRepository.enableForeignKeyChecks();
  }
}

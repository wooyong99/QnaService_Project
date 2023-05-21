package com.exam.jwy;

import com.exam.jwy.Question.Question;
import com.exam.jwy.Question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class PageTest {
    @Autowired
    QuestionRepository questionRepository;
    @Test
    void test(){
        Page<Question> page_q = questionRepository.findAll(Pageable.ofSize(10).withPage(2));
        Page<Question> page_q1 = questionRepository.findAll(PageRequest.of(2,10));
        System.out.println(page_q1.getTotalPages());
        System.out.println(page_q);
        }
}

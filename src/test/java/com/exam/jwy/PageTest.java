package com.exam.jwy;

import com.exam.jwy.Question.Question;
import com.exam.jwy.Question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class PageTest {
    @Autowired
    QuestionRepository questionRepository;
    @Test
    void test(){
        Page<Question> page_q = questionRepository.findAll(Pageable.ofSize(10).withPage(3));
        System.out.println(page_q.getNumberOfElements());
        System.out.println(page_q.getPageable().getOffset());

        }
}

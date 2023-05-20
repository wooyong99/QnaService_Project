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
        Page<Question> page_q = questionRepository.findAll(Pageable.ofSize(10).withPage(2));

        page_q.stream()
                .map(e -> e.getId())
                .forEach(System.out::println);
        System.out.println(page_q.getSize());
        System.out.println("========");
        if(page_q.hasNext()){
            Page<Question> page_q1 = questionRepository.findAll(page_q.nextPageable());
            page_q1.stream()
                    .map(e -> e.getId())
                    .forEach(System.out::println);
            System.out.println(page_q1.getSize());
        }

        System.out.println();
    }
}

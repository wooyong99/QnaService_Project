package com.exam.jwy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JwyApplicationTests {
	@Autowired
	QuestionRepository questionRepository;
	@Test
	public void testJpa(){
		Question q1 = new Question();
		q1.setSubject("제목입니다.");
		q1.setContent("내용입니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("제목2");
		q2.setContent("내용2");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);
	}
	@Test
	void testJpa2() {
		List<Question> arr = questionRepository.findAll();
		Question q = arr.get(0);
		assertEquals(2,arr.size());
		assertEquals(q.getSubject(),"제목입니다.");
	}

}

package com.exam.jwy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

		assertThat(q1.getId()).isGreaterThan(0);
		assertThat(q2.getId()).isGreaterThan(q1.getId());
	}


	@Test
	void testJpa2() {
//		findAll 메소드는 select * from question
		List<Question> arr = questionRepository.findAll();
		Question q = arr.get(0);
		assertEquals(2,arr.size());
		assertEquals(q.getSubject(),"제목입니다.");
	}
	@Test
	void testJpa3(){
		Question q = questionRepository.findBySubject("제목입니다.");
		assertEquals(q.getSubject(), "제목입니다.");
//		findBySubjectLike 메소드는 where subject LIKE "제목%"
		List<Question> qlist = questionRepository.findBySubjectLike("제목%");
		assertEquals(2,qlist.size());
		Question q1 = questionRepository.findById(2).get();
		System.out.println(q1.getId());
	}
	@Test
	void testJpa4(){
//		findBySubjectAndContent 메소드는 where subject = ? AND content = ?
		Question q = questionRepository.findBySubjectAndContent("제목2", "내용2");
		assertEquals(q.getId(),2);
	}
	@Test
	void testJpa5(){
		Question q = questionRepository.findById(1).get();
		q.setSubject("제목변경입니다.");
		questionRepository.save(q);
		assertEquals(q.getSubject(), "제목변경입니다.");
		questionRepository.delete(q);
		List<Question> qlist = questionRepository.findAll();
		assertEquals(qlist.size(), 1);
	}
	@Test
	void testJpa6(){
		List<Question> qlist = questionRepository.findBySubjectLike("%제목%");
		qlist.stream()
				.map(a -> ("id: "+a.getId()+" / subject : "+a.getSubject()))
				.forEach(System.out::println);
		Question q = questionRepository.findBySubjectAndContent("제목2","내용2");
		assertEquals(q.getId(), 2);
		q.setSubject("제목2변경");
		questionRepository.save(q);
		assertThat(q.getSubject()).isEqualTo("제목2변경");
	}
	@Test
	void testJpa7(){
		List<Question> qlist = questionRepository.findByIdLessThan(2);
		assertEquals(1,
				qlist.stream()
						.findFirst()
						.get()
						.getId());
		List<Question> qlist2 = questionRepository.findByIdGreaterThan(1);
		qlist2.stream()
				.map(a -> "id : "+a.getId()+" subject : "+a.getSubject())
				.forEach(System.out::println);
	}
	@Test
	void testJpa8(){
		questionRepository.truncate();
	}
	}

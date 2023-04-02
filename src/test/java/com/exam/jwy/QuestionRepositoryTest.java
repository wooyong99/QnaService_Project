package com.exam.jwy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class QuestionRepositoryTest {
	@Autowired
	QuestionRepository questionRepository;

	@BeforeEach
	void testJpa0(){
		clearData();
		createData();
	}
	void createData(){
		createData(questionRepository);
	}
	static void createData(QuestionRepository questionRepository){
		Question q1 = new Question();
		q1.setSubject("제목1");
		q1.setContent("내용1");
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
	void clearData(){
		clearData(questionRepository);
	}
	static void clearData(QuestionRepository questionRepository){
		questionRepository.truncateTable();
	}
	@Test
	void testJpa1(){
		List<Question> qlist = questionRepository.findBySubjectLike("제목%");
		qlist.stream()
				.map(a -> "제목 : "+a.getSubject()+" / 내용 : "+ a.getContent())
				.forEach(System.out::println);
	}
	@Test
	void testJpa2(){
		Question q1 = questionRepository.findBySubject("제목1");
		q1.setSubject("제목1변경");
		questionRepository.save(q1);

		Question q2 = questionRepository.findBySubject("제목2");
		q2.setSubject("제목2변경");
		questionRepository.save(q2);

		assertEquals(q1.getSubject(),"제목1변경");

		List<Question> qlist = questionRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
		qlist.stream()
				.map(a -> a.getId())
				.forEach(System.out::println);
	}
	@Test
	void testJps3(){
		Question q = questionRepository.findById(1).get();
		questionRepository.delete(q);
		List<Question> qlist = questionRepository.findAll();
		System.out.println(qlist.size());
	}
	@Test
	void testJps4(){
		createData();
		Question q1 = new Question();
		q1.setSubject("제목3");
		q1.setContent("내용3");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);

		Question q2 = questionRepository.findById(1).get();
		questionRepository.delete(q2);
		List<Question> qlist = questionRepository.findAll();
		System.out.println(qlist.size());
	}
	}

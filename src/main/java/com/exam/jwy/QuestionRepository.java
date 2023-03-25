package com.exam.jwy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
  Question findBySubject(String subject);

  List<Question> findBySubjectLike(String subject);

  Question findBySubjectAndContent(String content, String subject);

  List<Question> findByIdLessThan(int id);

  List<Question> findByIdGreaterThan(int id);
}

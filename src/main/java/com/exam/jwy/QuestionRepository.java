package com.exam.jwy;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface QuestionRepository extends JpaRepository<Question, Integer> {
  Question findBySubject(String subject);

  List<Question> findBySubjectLike(String subject);

  Question findBySubjectAndContent(String content, String subject);

  List<Question> findByIdLessThan(int id);

  List<Question> findByIdGreaterThan(int id);
  @Transactional
  @Modifying
  @Query(value="truncate table question", nativeQuery = true)
  void truncate();
}

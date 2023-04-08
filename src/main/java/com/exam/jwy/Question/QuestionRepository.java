package com.exam.jwy.Question;

import com.exam.jwy.base.RepositoryUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface QuestionRepository extends JpaRepository<Question, Integer>, RepositoryUtil {
  Question findBySubject(String subject);

  List<Question> findBySubjectLike(String subject);

  Question findBySubjectAndContent(String content, String subject);

  List<Question> findByIdLessThan(int id);

  List<Question> findByIdGreaterThan(int id);
  @Transactional
  @Modifying
  @Query(value="ALTER TABLE question AUTO_INCREMENT=1", nativeQuery = true)
  void truncate();

  List<Question> findBySubjectOrderById(String subject);

  List<Question> findBySubjectOrderByIdDesc(String subject);
}

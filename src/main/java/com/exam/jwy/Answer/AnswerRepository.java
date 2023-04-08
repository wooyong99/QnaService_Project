package com.exam.jwy.Answer;

import com.exam.jwy.base.RepositoryUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer>, RepositoryUtil {
  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE answer AUTO_INCREMENT=1", nativeQuery = true)
  void truncate();
  Answer findByContent(String content);
}

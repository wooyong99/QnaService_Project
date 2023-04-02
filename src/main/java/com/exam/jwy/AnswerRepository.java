package com.exam.jwy;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer>, RepositoryUtil {
  @Transactional
  @Modifying
  @Query(value = "truncate answer", nativeQuery = true)
  void truncate();
  Answer findByContent(String content);
}

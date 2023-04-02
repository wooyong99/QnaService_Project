package com.exam.jwy;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
  @Transactional
  @Modifying
  @Query(value = "truncate answer", nativeQuery = true)
  void truncate();

  @Transactional
  @Modifying
  @Query(value="SET FOREIGN_KEY_CHECKS = 1;", nativeQuery = true)
  void enableForeignKeyChecks();

  @Transactional
  @Modifying
  @Query(value="SET FOREIGN_KEY_CHECKS = 0;", nativeQuery = true)
  void disableForeignKeyChecks();
}

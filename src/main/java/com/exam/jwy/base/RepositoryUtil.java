package com.exam.jwy.base;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryUtil {

  @Transactional
  @Modifying
  @Query(value="SET FOREIGN_KEY_CHECKS = 0;", nativeQuery = true)
  void disableForeignKeyCheck();

  @Transactional
  @Modifying
  @Query(value="SET FOREIGN_KEY_CHECKS = 1;", nativeQuery = true)
  void enableForeignKeyCheck();

  void truncate();

  default void truncateTable(){
    truncate();
  }

}

package com.exam.jwy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Question{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length=200)
  private String subject;
  @Column(columnDefinition = "TEXT")
  private String content;
  private LocalDateTime createDate;
}

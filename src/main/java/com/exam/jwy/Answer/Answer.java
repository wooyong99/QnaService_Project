package com.exam.jwy.Answer;

import com.exam.jwy.Question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 200)
  private String content;
  private LocalDateTime createDate;
  @ManyToOne
  private Question question;
}

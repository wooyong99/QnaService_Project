package com.exam.jwy.Question;

import com.exam.jwy.Answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Question{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length=200)
  private String subject;
  @Column(columnDefinition = "LONGTEXT")
  private String content;
  private LocalDateTime createDate;
  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Answer> answerList = new ArrayList<>();

  public void addAnswer(Answer answer){
    answer.setQuestion(this);
    getAnswerList().add(answer);
  }
  public int answerCount(){
    return this.answerList.size();
  }
}
